package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.AssignmentRequestModel;
import com.practice.online_diagnost.api.models.DiagnosRequestModel;
import com.practice.online_diagnost.api.models.builders.DiagnosResponseModelBuilder;
import com.practice.online_diagnost.services.AssignmentService;
import com.practice.online_diagnost.services.DiagnosService;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.builders.AssignmentDomainBuilder;
import com.practice.online_diagnost.services.domains.builders.DiagnosDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Secured
@Path("/diagnoses")
public class DiagnosResourse {
    private static final Logger LOG = Logger.getLogger(DiagnosResourse.class.getSimpleName());

    @SneakyThrows
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDiagnos(DiagnosRequestModel diagnosRequestModel) {
        System.out.println(diagnosRequestModel + " post");
        DiagnosService diagnosService =
                (DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE);
        int id = diagnosService.add(new DiagnosDomainBuilder().create(diagnosRequestModel));
        return id != -1 ? Response.ok().build() : Response.serverError().build();
    }
}
