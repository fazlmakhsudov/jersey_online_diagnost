package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.PatientEntity;
import com.practice.online_diagnost.services.domains.PatientDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PatientEntityBuilder {
    public PatientEntity create(PatientDomain patientDomain) {
        return PatientEntity.builder()
                .id(patientDomain.getId())
                .diseasesId(patientDomain.getDiseasesId())
                .condition(Objects.isNull(patientDomain.getCondition()) ? ""
                        : patientDomain.getCondition())
                .build();
    }

    public List<PatientEntity> create(List<PatientDomain> patientDomainList) {
        return patientDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
