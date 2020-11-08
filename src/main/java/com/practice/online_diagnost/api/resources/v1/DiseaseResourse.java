package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.DiseaseResponseModel;
import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.api.models.builders.DiseaseResponseModelBuilder;
import com.practice.online_diagnost.api.models.builders.TreatmentHistoryResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.DiseaseService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.TreatmentHistoryService;
import com.practice.online_diagnost.services.UserService;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Secured
@Path("/diseases")
public class DiseaseResourse {
    private static final Logger LOG = Logger.getLogger(DiseaseResourse.class.getSimpleName());


    @Path("/patient")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentHistoryResponseModel getPatientTreatmentHistory(ContainerRequestContext requestContext) throws ServiceException {
        TreatmentHistoryService treatmentHistoryService = (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        int patientId = TokenServiceImpl.getInstance().getOwnerId(token);

        TreatmentHistoryDomain treatmentHistoryDomain = treatmentHistoryService.findForPatients(patientId);

        return Objects.isNull(treatmentHistoryDomain) ? new TreatmentHistoryResponseModel()
                : new TreatmentHistoryResponseModelBuilder().create(treatmentHistoryDomain);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, DiseaseResponseModel> getDiseases(ContainerRequestContext requestContext) throws ServiceException {
        DiseaseService diseaseService = (DiseaseService) ServiceFactory.createService(ServiceType.DISEASE_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String medicEmail = TokenServiceImpl.getInstance().getEmail(token);
        int medicId = TokenServiceImpl.getInstance().getOwnerId(token);

        UserDomain userDomain = userService.find(medicEmail);
        List<DiseaseDomain> diseaseDomains = null;

        if (!Objects.isNull(userDomain)
                && (userDomain.getRolesId() == 2 || userDomain.getRolesId() == 1)
                && userDomain.getMedicsId() == medicId) {
            diseaseDomains = diseaseService.findAll();
        }

        return Objects.isNull(diseaseDomains) ? new HashMap<>()
                : new DiseaseResponseModelBuilder().create(diseaseDomains).stream()
                .collect(Collectors.toMap(DiseaseResponseModel::getId, diseaseResponseModel -> diseaseResponseModel));
    }

}
