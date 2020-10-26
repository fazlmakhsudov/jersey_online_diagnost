package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.TestRequestModel;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.DiagnosService;
import com.practice.online_diagnost.services.TokenService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;


@Path("/test")
public class TestResourse {


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

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(TestRequestModel testRequestModel) {
        System.out.println(testRequestModel);
        return Response.ok(testRequestModel).build();
    }

    @Path("{searchParameter}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosDomain> getAuthors(@PathParam("searchParameter") String searchParameter) throws ServiceException {

        return ((DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE)).findForTreatmentHistories(Integer.parseInt(searchParameter));
    }
}
