package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.TreatmentHistoryResponseModel;
import com.practice.online_diagnost.api.models.builders.TreatmentHistoryResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.DiagnosService;
import com.practice.online_diagnost.services.TokenServiceImpl;
import com.practice.online_diagnost.services.TreatmentHistoryService;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


@Path("/treatment-history")
public class TreatmentHistoryResourse {
    private static final Logger LOG = Logger.getLogger(TreatmentHistoryResourse.class.getSimpleName());

    @Path("/user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentHistoryResponseModel getPatientTreatmentHistory(ContainerRequestContext requestContext) throws ServiceException {
        TreatmentHistoryService treatmentHistoryService = (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        int patientId = TokenServiceImpl.getInstance().getId(token);

        TreatmentHistoryDomain treatmentHistoryDomain = treatmentHistoryService.findForPatients(patientId);

        return Objects.isNull(treatmentHistoryDomain) ? new TreatmentHistoryResponseModel()
                : new TreatmentHistoryResponseModelBuilder().create(treatmentHistoryDomain);
    }


    @Path("{searchParameter}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosDomain> getAuthors(@PathParam("searchParameter") String searchParameter) throws ServiceException {

        return ((DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE)).findForTreatmentHistories(Integer.parseInt(searchParameter));
    }


    private String encryptPassword(final String password) {
        if (Objects.isNull(password) || password.isEmpty()) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOG.severe(e.getMessage());
        }
        return "";
    }
}
