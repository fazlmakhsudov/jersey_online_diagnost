package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.TestRequestModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;


@Path("/test")
public class TestResourse {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAuthors() {
        return "hello bou";
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(TestRequestModel testRequestModel) {
        System.out.println(testRequestModel);
        return Response.ok(testRequestModel).build();
    }
}
