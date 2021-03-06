package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.UserRequestModel;
import com.practice.online_diagnost.api.models.UserResponseModel;
import com.practice.online_diagnost.api.models.builders.UserResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.*;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.domains.builders.UserDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Path("/users")
public class UserResourse {
    private static final Logger LOG = Logger.getLogger(UserResourse.class.getSimpleName());
    private static final String NO_DIAGNOS = "no_diagnos";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosDomain> getAuthors() throws ServiceException {

        return ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE).findAll();
    }


    @SneakyThrows
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserRequestModel userRequestModel) {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        TreatmentHistoryService treatmentHistoryService = (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        DiagnosService diagnosService = (DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE);


        StringBuilder sb = new StringBuilder();
        int userId = -1;
        int patientId = -1;
        int treatmentHistoryId = -1;
        int diagnosId = -1;

        boolean error = false;
        try {
            PatientDomain patientDomain = new PatientDomain();
            patientDomain.setTreatmentHistory(new TreatmentHistoryDomain());
            patientDomain.setDiseasesId(-1);
            patientId = patientService.add(patientDomain);
            patientDomain.getTreatmentHistory().setPatientsId(patientId);

            userRequestModel.setRolesId(3);
            userRequestModel.setPatientsId(patientId);
            userId = userService.add(new UserDomainBuilder().create(userRequestModel));

            treatmentHistoryId = treatmentHistoryService.add(patientDomain.getTreatmentHistory());

            DiagnosDomain diagnosDomain = new DiagnosDomain();
            diagnosDomain.setName(NO_DIAGNOS);
            diagnosDomain.setTreatmentHistoriesId(treatmentHistoryId);
            diagnosId = diagnosService.add(diagnosDomain);

            String token = TokenServiceImpl.getInstance().generate(userRequestModel.getEmail(), patientId, diagnosId);
            sb.append("{");
            sb.append(String.format("\"token\":\"%s\",", token));
            sb.append(String.format("\"diagnosId\":\"%d\",", diagnosId));
            sb.append(String.format("\"role\":%d", 3));
            sb.append("}");
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            error = true;
            patientService.remove(patientId);
            sb = new StringBuilder(e.getMessage());
        }

        return !error && userId != -1 ? Response.ok(sb.toString()).build() : Response.serverError().build();
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(UserRequestModel userRequestModel) {
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        TreatmentHistoryService treatmentHistoryService =
                (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);

        StringBuilder sb = new StringBuilder();

        boolean error = false;
        try {
            UserDomain userDomain = userService.find(userRequestModel.getEmail());

            int diagnosId = -1;
            String token = "";
            if (userDomain.getRolesId() == 3) {
                TreatmentHistoryDomain treatmentHistoryDomain
                        = treatmentHistoryService.findForPatients(userDomain.getPatientsId());
                diagnosId = treatmentHistoryDomain.getDiagnoses().stream()
                        .filter(diagnosDomain -> diagnosDomain.getName().equals(NO_DIAGNOS))
                        .map(DiagnosDomain::getId).findFirst().orElse(-1);
                token = TokenServiceImpl.getInstance()
                        .generate(userDomain.getEmail(), userDomain.getPatientsId(), diagnosId);
            } else if (userDomain.getRolesId() == 2) {
                token = TokenServiceImpl.getInstance()
                        .generate(userDomain.getEmail(), userDomain.getMedicsId(), diagnosId);
            } else {
                // for Admin model
            }

            if (!Objects.isNull(userDomain) && !Objects.isNull(userRequestModel.getPassword())
                    && userDomain.getPassword().equals(encryptPassword(userRequestModel.getPassword()))) {
                sb.append("{");
                sb.append(String.format("\"token\":\"%s\",", token));
                if (userDomain.getRolesId() == 3) {
                    sb.append(String.format("\"diagnosId\":\"%d\",", diagnosId));
                }
                sb.append(String.format("\"role\":%d", userDomain.getRolesId()));
                sb.append("}");
            } else {
                error = true;
            }

        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            error = true;
        }

        return !error ? Response.ok(sb.toString()).build() : Response.serverError().build();
    }

    @Secured
    @PUT
    @Path("/save")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(UserRequestModel userRequestModel) {

        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        StringBuilder sb = new StringBuilder();

        boolean error = false;
        try {
            UserDomain userDomain = userService.find(userRequestModel.getId());

            if (!Objects.isNull(userDomain) && !Objects.isNull(userRequestModel.getPassword())) {
                userDomain.setName(!Validator.isValidString(userRequestModel.getName()) ? userDomain.getName() : userRequestModel.getName());

                userDomain.setSurname(!Validator.isValidString(userRequestModel.getSurname()) ? userDomain.getSurname() : userRequestModel.getSurname());

                userDomain.setBirthdate(!Validator.isValidDate(userRequestModel.getBirthdate()) ? userDomain.getBirthdate() : userRequestModel.getBirthdate());
                userDomain.setEmail(!Validator.isValidString(userRequestModel.getEmail()) ? userDomain.getEmail() : userRequestModel.getEmail());
                userDomain.setPhone(!Validator.isValidString(userRequestModel.getPhone()) ? userDomain.getPhone() : userRequestModel.getPhone());

                userDomain.setPassword(!Validator.isValidString(userRequestModel.getPassword())
                        || userDomain.getPassword().equals(userRequestModel.getPassword())
                        ? userDomain.getPassword()
                        : encryptPassword(userRequestModel.getPassword()));


                userDomain.setGender(!Validator.isValidString(userRequestModel.getGender()) ? userDomain.getGender() : userRequestModel.getGender());
                userDomain.setLocation(!Validator.isValidString(userRequestModel.getLocation()) ? userDomain.getLocation() : userRequestModel.getLocation());
                if (userService.save(userDomain)) {
                    String token = TokenServiceImpl.getInstance().generate(userDomain.getEmail(), userDomain.getPatientsId());
                    sb.append("{");
                    sb.append(String.format("\"token\":\"%s\",", token));
                    sb.append(String.format("\"role\":%d", userDomain.getRolesId()));
                    sb.append("}");
                } else {
                    error = true;
                }
            } else {
                error = true;
            }

        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            error = true;
        }
        return !error ? Response.ok(sb.toString()).build() : Response.serverError().build();
    }


    @SneakyThrows
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/single")
    public Response getBook(ContainerRequestContext requestContext) {
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String email = TokenServiceImpl.getInstance().getEmail(token);
        UserDomain userDomain = Validator.isValidString(email) ? userService.find(email) : null;

        UserResponseModel userResponseModel = null;
        if (!Objects.isNull(userDomain)) {
            userResponseModel = new UserResponseModelBuilder().create(userDomain);
        }

        return Objects.isNull(userResponseModel) ? Response.status(Response.Status.NOT_FOUND).build() :
                Response.ok(userResponseModel).build();
    }


    private String encryptPassword(final String password) {
        if (Objects.isNull(password) || password.isEmpty()) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOG.severe(e.getMessage());
        }
        return "";
    }
}
