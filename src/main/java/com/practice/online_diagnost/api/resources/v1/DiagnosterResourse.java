package com.practice.online_diagnost.api.resources.v1;

import com.practice.online_diagnost.api.models.DiagnosterRequestModel;
import com.practice.online_diagnost.api.models.DiagnosterResponseModel;
import com.practice.online_diagnost.services.DiseaseService;
import com.practice.online_diagnost.services.domains.DiagnosterService;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;
import lombok.SneakyThrows;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Path("/diagnoster")
public class DiagnosterResourse {
    private static final Logger LOG = Logger.getLogger(DiagnosterResourse.class.getSimpleName());

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
                diagnosterService.predictDiagnos(diagnosterRequestModel).entrySet()
                        .stream().map(entry -> DiagnosterResponseModel.builder()
                        .diseaseName(diseaseMap.get(entry.getKey()))
                        .probability(entry.getValue()).build())
                        .collect(Collectors.toList());
        return Objects.isNull(diagnosterResponseModels) || diagnosterResponseModels.size() == 0
                ? Response.noContent().build() : Response.ok(diagnosterResponseModels).build();
    }
}
