package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.RoleRequestModel;
import com.practice.online_diagnost.api.models.RoleResponseModel;
import com.practice.online_diagnost.api.models.builders.RoleResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.RoleService;
import com.practice.online_diagnost.services.domains.RoleDomain;
import com.practice.online_diagnost.services.domains.builders.RoleDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED;


@Path("/role")
public class RoleResource {
    private static final Logger LOG = Logger.getLogger("RoleResourse");
     private final RoleService roleService = (RoleService) ServiceFactory.createService(ServiceType.ROLE_SERVICE);
    private final RoleResponseModelBuilder roleResponseModelBuilder = new RoleResponseModelBuilder();
    private final RoleDomainBuilder roleDomainBuilder = new RoleDomainBuilder();

//    @Secured
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRole(RoleRequestModel RoleModel) {
        int id = 0;
        String message = "";
        try {
            id = roleService.add(roleDomainBuilder.create(RoleModel));
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            message = e.getMessage();
        }

        return id > 0 ? Response.status(Response.Status.CREATED).build() : Response.status(NOT_IMPLEMENTED.getStatusCode(), message).build();
//        return id > 0 ? Response.status(Response.Status.CREATED).build() : Response.serverError().build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoleResponseModel> getRoles() {
        List<RoleResponseModel> roleResponseModels = new ArrayList<>();
        try {
            roleResponseModels = roleResponseModelBuilder.create(roleService.findAll());
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
        }
        return roleResponseModels;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{searchParameter}")
    public Response getRole(@PathParam("searchParameter") String searchParameter) {
        RoleDomain roleDomain = null;
        String message = "";
        try {
            roleDomain = searchParameter.matches("\\d+") ?
                    roleService.find(Integer.parseInt(searchParameter)) : roleService.find(searchParameter);
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            message = e.getMessage();
        }
        return Objects.isNull(roleDomain) ? Response.status(Response.Status.NOT_FOUND.getStatusCode(), message).build() :
                Response.ok(roleResponseModelBuilder.create(roleDomain)).build();
    }

//    @Secured
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRole(RoleRequestModel RoleModel) {
        boolean flag = false;
        String message = "";
        try {
            flag = roleService.save(roleDomainBuilder.create(RoleModel));
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            message = e.getMessage();
        }
        return flag ? Response.ok().build() : Response.status(NOT_IMPLEMENTED.getStatusCode(), message).build();
//        return flag ? Response.ok().build() : Response.serverError().build();
    }

//    @Secured
    @DELETE
    @Path("{id}")
    public Response deleteRole(@PathParam("id") int id) {
        boolean flag = false;
        String message = "";
        try {
            flag = roleService.remove(id);
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
            message = e.getMessage();
        }
        return flag ? Response.ok().build() : Response.status(NOT_IMPLEMENTED.getStatusCode(),message).build();
//        return flag ? Response.ok().build() : Response.serverError().build();
    }
}
