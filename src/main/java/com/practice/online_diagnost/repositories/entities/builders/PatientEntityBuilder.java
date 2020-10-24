package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.PatientEntity;
import com.practice.online_diagnost.repositories.entities.RoleEntity;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.RoleDomain;

import java.util.List;
import java.util.stream.Collectors;

public class PatientEntityBuilder {
    public PatientEntity create(PatientDomain patientDomain) {
        return PatientEntity.builder()
                .id(patientDomain.getId())
                .treatmentHistory(new TreatmentHistoryEntityBuilder().create(patientDomain.getTreatmentHistory()))
                .diseasesId(patientDomain.getDiseasesId())
                .userEntity(new UserEntityBuilder().create(patientDomain.getUserDomain()))
                .build();
    }

    public List<PatientEntity> create(List<PatientDomain> patientDomainList) {
        return patientDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
