package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.DiseaseResponseModel;
import com.practice.online_diagnost.services.domains.DiseaseDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class DiseaseResponseModelBuilder {
    public DiseaseResponseModel create(DiseaseDomain diseaseDomain) {
        return DiseaseResponseModel.builder()
                .id(diseaseDomain.getId())
                .name(diseaseDomain.getName())
                .patients(new PatientResponseModelBuilder().create(diseaseDomain.getPatients()))
                .symptoms(new SymptomResponseModelBuilder().create(diseaseDomain.getSymptoms()))
                .createdDate(diseaseDomain.getCreatedDate())
                .build();
    }

    public List<DiseaseResponseModel> create(List<DiseaseDomain> diseaseDomains) {
        return diseaseDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
