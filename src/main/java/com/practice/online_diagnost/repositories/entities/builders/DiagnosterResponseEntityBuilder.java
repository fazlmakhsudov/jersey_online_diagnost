package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.DiagnosterResponseEntity;
import com.practice.online_diagnost.services.domains.DiagnosterResponseDomain;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosterResponseEntityBuilder {
    public DiagnosterResponseEntity create(DiagnosterResponseDomain diagnosterResponseDomain) {
        return DiagnosterResponseEntity.builder()
                .diseaseName(diagnosterResponseDomain.getDiseaseName())
                .probability(diagnosterResponseDomain.getProbability())
                .patientsId(diagnosterResponseDomain.getPatientsId())
                .symptoms(diagnosterResponseDomain.getSymptoms())
                .build();
    }

    public List<DiagnosterResponseEntity> create(List<DiagnosterResponseDomain> diagnosterResponseDomains) {
        return diagnosterResponseDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
