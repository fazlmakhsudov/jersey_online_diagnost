package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.SymptomEntity;
import com.practice.online_diagnost.services.domains.SymptomDomain;

import java.util.List;
import java.util.stream.Collectors;

public class SymptomEntityBuilder {
    public SymptomEntity create(SymptomDomain symptomDomain) {
        return SymptomEntity.builder()
                .id(symptomDomain.getId())
                .name(symptomDomain.getName())
                .diagnosesId(symptomDomain.getDiagnosesId())
                .diseasesId(symptomDomain.getDiseasesId())
                .createdDate(symptomDomain.getCreatedDate())
                .updatedDate(symptomDomain.getUpdatedDate())
                .build();
    }

    public List<SymptomEntity> create(List<SymptomDomain> symptomDomainLIst) {
        return symptomDomainLIst.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
