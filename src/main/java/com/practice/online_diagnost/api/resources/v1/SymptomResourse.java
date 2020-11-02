package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.SymptomRequestModel;
import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.api.models.builders.TreatmentHistoryResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.SymptomService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.TreatmentHistoryService;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.domains.builders.SymptomDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.logging.Logger;

@Secured
@Path("/symptoms")
public class SymptomResourse {
    private static final Logger LOG = Logger.getLogger(SymptomResourse.class.getSimpleName());

    @SneakyThrows
    @Path("/patient")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSymptom(SymptomRequestModel symptomRequestModel) {
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        int rowInserted = symptomService.add(new SymptomDomainBuilder().create(symptomRequestModel));
        return rowInserted > 0 ? Response.ok().build() : Response.serverError().build();
    }
}
