package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.AssignmentRequestModel;
import com.practice.online_diagnost.services.AssignmentService;
import com.practice.online_diagnost.services.domains.builders.AssignmentDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Secured
@Path("/assignments")
public class AssignmentResourse {
    private static final Logger LOG = Logger.getLogger(AssignmentResourse.class.getSimpleName());

    @SneakyThrows
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAssignment(AssignmentRequestModel assignmentRequestModel) {
        System.out.println(assignmentRequestModel);
        AssignmentService assignmentService =
                (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        int rowInserted = assignmentService.add(new AssignmentDomainBuilder().create(assignmentRequestModel));
        return rowInserted > 0 ? Response.ok().build() : Response.serverError().build();
    }

    @SneakyThrows
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAssignment(AssignmentRequestModel assignmentRequestModel) {
        System.out.println(assignmentRequestModel);
        AssignmentService assignmentService =
                (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        boolean updated = assignmentService.save(new AssignmentDomainBuilder().create(assignmentRequestModel));
        return updated ? Response.ok().build() : Response.serverError().build();
    }
}
