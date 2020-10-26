package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.SymptomResponseModel;
import com.practice.online_diagnost.services.domains.SymptomDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SymptomResponseModelBuilder {
    public SymptomResponseModel create(SymptomDomain symptomDomain) {
        return SymptomResponseModel.builder()
                .id(symptomDomain.getId())
                .name(symptomDomain.getName())
                .diagnosesId(symptomDomain.getDiagnosesId())
                .diseasesId(symptomDomain.getDiseasesId())
                .createdDate(symptomDomain.getCreatedDate())
                .updatedDate(symptomDomain.getUpdatedDate())
                .build();
    }

    public List<SymptomResponseModel> create(List<SymptomDomain> symptomDomains) {
        return symptomDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
