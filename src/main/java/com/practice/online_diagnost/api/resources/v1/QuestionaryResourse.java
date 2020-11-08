package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.builders.QuestionaryResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.QuestionaryService;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.logging.Logger;


@Path("/questionaries")
public class QuestionaryResourse {
    private static final Logger LOG = Logger.getLogger(QuestionaryResourse.class.getSimpleName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions() throws ServiceException {
        LOG.info("getQuestions() starts");
        QuestionaryService questionaryService =
                (QuestionaryService) ServiceFactory.createService(ServiceType.QUESTIONARY_SERVICE);
        QuestionaryDomain questionaryDomain = questionaryService.find("common");

        LOG.info("getQuestions() ends");
        return Objects.isNull(questionaryDomain) ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(new QuestionaryResponseModelBuilder().create(questionaryDomain)).build();
    }

//    @QueryParam("patientsId") int patientsId,
//    @QueryParam("question1") String question1,
//    @QueryParam("question2") String question2

}
