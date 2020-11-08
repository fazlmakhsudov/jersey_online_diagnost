package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.PatientResponseModel;
import com.practice.online_diagnost.api.models.builders.MedicResponseModelBuilder;
import com.practice.online_diagnost.api.models.builders.PatientResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.MedicService;
import com.practice.online_diagnost.services.PatientService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.UserService;
import com.practice.online_diagnost.services.domains.MedicDomain;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Secured
@Path("/medics")
public class MedicResourse {
    private static final Logger LOG = Logger.getLogger(MedicResourse.class.getSimpleName());


    @Path("/single")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicResponseModel(ContainerRequestContext requestContext) throws ServiceException {

        MedicService medicService = (MedicService) ServiceFactory.createService(ServiceType.MEDIC_SERVICE);
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        int medicId = TokenServiceImpl.getInstance().getOwnerId(token);
        MedicDomain medicDomain = medicService.find(medicId);

        return Objects.isNull(medicDomain) ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(new MedicResponseModelBuilder().create(medicDomain)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PatientResponseModel> getPatients(ContainerRequestContext requestContext) throws ServiceException {
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
        }
        return Objects.isNull(patientDomains) ?
                new ArrayList<>() : new PatientResponseModelBuilder().create(patientDomains);
    }

}
