package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.api.models.builders.TreatmentHistoryResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.TreatmentHistoryService;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Secured
@Path("/treatment-history")
public class TreatmentHistoryResourse {
    private static final Logger LOG = Logger.getLogger(TreatmentHistoryResourse.class.getSimpleName());


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
    public List<TreatmentHistoryResponseModel> getPatientTreatmentHistories(ContainerRequestContext requestContext) throws ServiceException {
        TreatmentHistoryService treatmentHistoryService = (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        int ownerId = TokenServiceImpl.getInstance().getOwnerId(token);

        List<TreatmentHistoryDomain> treatmentHistoryDomains = treatmentHistoryService.findAll();

        return ownerId != 2 && ownerId != 1 ? new ArrayList<>()
                : new TreatmentHistoryResponseModelBuilder().create(treatmentHistoryDomains);
    }

}
