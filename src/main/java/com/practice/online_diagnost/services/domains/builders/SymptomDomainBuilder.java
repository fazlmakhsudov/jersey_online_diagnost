package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.SymptomRequestModel;
import com.practice.online_diagnost.repositories.entities.SymptomEntity;
import com.practice.online_diagnost.services.domains.SymptomDomain;

import java.util.List;
import java.util.stream.Collectors;

public class SymptomDomainBuilder {
    public SymptomDomain create(SymptomRequestModel symptomModel) {
        return SymptomDomain.builder()
                .id(symptomModel.getId())
                .name(symptomModel.getName())
                .diagnosesId(symptomModel.getDiagnosesId())
                .diseasesId(symptomModel.getDiseasesId())

                .build();
    }

    public List<SymptomDomain> create2(List<SymptomRequestModel> symptomRequestModelList) {
        return symptomRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public SymptomDomain create(SymptomEntity symptomEntity) {
        return SymptomDomain.builder()
                .id(symptomEntity.getId())
                .name(symptomEntity.getName())
                .diagnosesId(symptomEntity.getDiagnosesId())
                .diseasesId(symptomEntity.getDiseasesId())
                .createdDate(symptomEntity.getCreatedDate())
                .updatedDate(symptomEntity.getUpdatedDate())
                .build();
    }

    public List<SymptomDomain> create(List<SymptomEntity> symptomEntityList) {
        return symptomEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
