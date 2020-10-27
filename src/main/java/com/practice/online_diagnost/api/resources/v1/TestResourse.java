package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.UserRequestModel;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.DiagnosService;
import com.practice.online_diagnost.services.PatientService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.UserService;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.domains.builders.UserDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;


@Path("/test")
public class TestResourse {
    private static final Logger LOG = Logger.getLogger("TestResourse");


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosDomain> getAuthors() throws ServiceException {

        return ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE).findAll();
    }

    @POST
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testToken(String email) throws ServiceException {
        String token = TokenServiceImpl.getInstance().generate(email);
        return Response.ok("Bearer " + token).header(AUTHORIZATION, "Bearer " + token)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON).build();


    }


    @SneakyThrows
    @POST
    @Path("/register")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserRequestModel userRequestModel) {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        StringBuilder sb = new StringBuilder();
        int userId = -1;
        int patientId = -1;

        boolean error = false;
        try {
            PatientDomain patientDomain = new PatientDomain();
            patientDomain.setDiseasesId(-1);
            patientId = patientService.add(patientDomain);

            userRequestModel.setRolesId(3);
            userRequestModel.setPatientsId(patientId);
            userId = userService.add(new UserDomainBuilder().create(userRequestModel));
            String token = TokenServiceImpl.getInstance().generate(userRequestModel.getEmail());
            sb.append("{");
            sb.append(String.format("\"token\":\"%s\",", token));
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

        StringBuilder sb = new StringBuilder();

        boolean error = false;
        try {
            UserDomain userDomain = userService.find(userRequestModel.getEmail());
            String token = TokenServiceImpl.getInstance().generate(userDomain.getEmail());
            if (!Objects.isNull(userDomain) && !Objects.isNull(userRequestModel.getPassword())
                    && userDomain.getPassword().equals(encryptPassword(userRequestModel.getPassword()))) {
                sb.append("{");
                sb.append(String.format("\"token\":\"%s\",", token));
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

    @Path("{searchParameter}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosDomain> getAuthors(@PathParam("searchParameter") String searchParameter) throws ServiceException {

        return ((DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE)).findForTreatmentHistories(Integer.parseInt(searchParameter));
    }


    private String encryptPassword(final String password)  {
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
