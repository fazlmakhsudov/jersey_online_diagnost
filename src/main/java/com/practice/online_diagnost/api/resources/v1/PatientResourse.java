package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.PatientRequestModel;
import com.practice.online_diagnost.api.models.PatientResponseModel;
import com.practice.online_diagnost.api.models.builders.PatientResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.PatientService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.UserService;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.domains.builders.PatientDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Secured
@Path("/patients")
public class PatientResourse {
    private static final Logger LOG = Logger.getLogger(PatientResourse.class.getSimpleName());


//    @Path("/patient")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public TreatmentHistoryResponseModel getPatientTreatmentHistory(ContainerRequestContext requestContext) throws ServiceException {
//        TreatmentHistoryService treatmentHistoryService = (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
//        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        int patientId = TokenServiceImpl.getInstance().getOwnerId(token);
//
//        TreatmentHistoryDomain treatmentHistoryDomain = treatmentHistoryService.findForPatients(patientId);
//
//        return Objects.isNull(treatmentHistoryDomain) ? new TreatmentHistoryResponseModel()
//                : new TreatmentHistoryResponseModelBuilder().create(treatmentHistoryDomain);
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PatientResponseModel> getPatients(ContainerRequestContext requestContext) throws ServiceException {
        LOG.info("getPatients() starts");
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String medicEmail = TokenServiceImpl.getInstance().getEmail(token);
        int medicId = TokenServiceImpl.getInstance().getOwnerId(token);

        UserDomain userDomain = userService.find(medicEmail);
        List<PatientDomain> patientDomains = null;

        if (!Objects.isNull(userDomain)
                && (userDomain.getRolesId() == 2 || userDomain.getRolesId() == 1)
                && userDomain.getMedicsId() == medicId) {
            patientDomains = patientService.findAll();
            patientDomains = patientDomains.stream().filter(patientDomain -> {
                return !Objects.isNull(patientDomain.getTreatmentHistory())
                        && !Objects.isNull(patientDomain.getUserDomain());
            }).collect(Collectors.toList());
        }
        LOG.info("getPatients() ends");
        return Objects.isNull(patientDomains) ?
                new ArrayList<>() : new PatientResponseModelBuilder().create(patientDomains);
    }

    @Path("/no-diagnoses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PatientResponseModel> getPatientsNoDiagnoses(ContainerRequestContext requestContext) throws ServiceException {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String medicEmail = TokenServiceImpl.getInstance().getEmail(token);
        int medicId = TokenServiceImpl.getInstance().getOwnerId(token);

        UserDomain userDomain = userService.find(medicEmail);
        List<PatientDomain> patientDomains = null;

        if (!Objects.isNull(userDomain)
                && (userDomain.getRolesId() == 2 || userDomain.getRolesId() == 1)
                && userDomain.getMedicsId() == medicId) {
            patientDomains = patientService.findAll();
            System.out.println(patientDomains);
            patientDomains = patientDomains.stream()
                    .filter(patientDomain -> !Objects.isNull(patientDomain.getTreatmentHistory()))
                    .map(patientDomain -> {
                        List<DiagnosDomain> diagnosDomains = patientDomain.getTreatmentHistory().getDiagnoses();
                        diagnosDomains = diagnosDomains.stream()
                                .filter(diagnosDomain -> diagnosDomain.getName().equals("no_diagnos"))
                                .collect(Collectors.toList());
                        patientDomain.getTreatmentHistory().setDiagnoses(diagnosDomains);
                        return patientDomain;
                    }).collect(Collectors.toList());
        }

        return Objects.isNull(patientDomains) ?
                new ArrayList<>() : new PatientResponseModelBuilder().create(patientDomains);
    }

    @SneakyThrows
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(PatientRequestModel patientRequestModel) {
        LOG.info("updatePatient() starts");
        PatientService patientService =
                (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        boolean updated = patientService.save(new PatientDomainBuilder().create(patientRequestModel));
        LOG.info("getPatients() ends");
        return updated ? Response.ok().build() : Response.serverError().build();
    }

}
