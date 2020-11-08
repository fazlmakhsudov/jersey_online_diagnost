package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.filters.Secured;
import com.practice.online_diagnost.api.models.DiagnosterRequestModel;
import com.practice.online_diagnost.api.models.DiagnosterResponseModel;
import com.practice.online_diagnost.api.models.QuestionRequestModel;
import com.practice.online_diagnost.api.models.builders.DiagnosterResponseModelBuilder;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.*;
import com.practice.online_diagnost.services.domains.DiagnosterResponseDomain;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.domains.builders.DiagnosterResponseDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Path("/diagnoster")
public class DiagnosterResourse {
    private static final Logger LOG = Logger.getLogger(DiagnosterResourse.class.getSimpleName());
    private static final String tableName = "diagnoster_responses";

    @SneakyThrows
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response predictDisease(DiagnosterRequestModel diagnosterRequestModel) {
        DiseaseService diseaseService = (DiseaseService) ServiceFactory.createService(ServiceType.DISEASE_SERVICE);
        List<DiseaseDomain> diseaseDomains = diseaseService.findAll();
        Map<Integer, String> diseaseMap = diseaseDomains.stream()
                .collect(Collectors.toMap(DiseaseDomain::getId, DiseaseDomain::getName));


        DiagnosterService diagnosterService = new DiagnosterService();
        List<DiagnosterResponseModel> diagnosterResponseModels =
                diagnosterService.predictDiagnos(diagnosterRequestModel, "statistic_data").entrySet()
                        .stream().map(entry -> DiagnosterResponseModel.builder()
                        .diseaseName(diseaseMap.get(entry.getKey()))
                        .probability(entry.getValue()).build())
                        .collect(Collectors.toList());

        String email = diagnosterRequestModel.getEmail();
        if (email.matches(".+[@].+[.].{2,3}")) {
            UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
            UserDomain userDomain = userService.find(email);
            if (!Objects.isNull(userDomain)) {
                int patientsId = userDomain.getPatientsId();
                String symptoms = diagnosterRequestModel.getQuestions().stream()
                        .filter(quesetion -> quesetion.getAnswer().matches("true"))
                        .map(QuestionRequestModel::getName)
                        .reduce((s1, s2) -> s1 + "; " + s2).orElse("no_symptoms");
                diagnosterResponseModels.forEach(diagnosterResponseModel -> {
                    diagnosterResponseModel.setPatientsId(patientsId);
                    diagnosterResponseModel.setSymptoms(symptoms);
                });
                System.out.println("********&&&&*********");
                diagnosterResponseModels.forEach(System.out::println);

                DiagnosterResponseService diagnosterResponseService = new DiagnosterResponseService();

                diagnosterResponseService.addBatch(new DiagnosterResponseDomainBuilder().create2(diagnosterResponseModels), tableName);
            }
        }

        return Objects.isNull(diagnosterResponseModels) || diagnosterResponseModels.size() == 0
                ? Response.noContent().build() : Response.ok(diagnosterResponseModels).build();
    }

    @SneakyThrows
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiagnosterResponseModel> getDiagnosterResponseModelsForPatient(ContainerRequestContext requestContext) {
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);

        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String email = TokenServiceImpl.getInstance().getEmail(token);
        int patientsId = TokenServiceImpl.getInstance().getOwnerId(token);
        UserDomain userDomain = userService.find(email);
        List<DiagnosterResponseModel> diagnosterResponseModels = new ArrayList<>();
        if (Objects.isNull(userDomain) || userDomain.getPatientsId() != patientsId) {
            return diagnosterResponseModels;
        }
        try {
            DiagnosterResponseService diagnosterResponseService = new DiagnosterResponseService();
            List<DiagnosterResponseDomain> diagnosterResponseDomains = diagnosterResponseService.findAllForPatient(tableName, patientsId);
            DiagnosterResponseModelBuilder diagnosterResponseModelBuilder = new DiagnosterResponseModelBuilder();
            diagnosterResponseModels = diagnosterResponseModelBuilder.create(diagnosterResponseDomains);
        } catch (ServiceException e) {
            LOG.severe(e.getMessage());
        }
        return diagnosterResponseModels;
    }
}
