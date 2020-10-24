package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.repositories.entities.DiseaseEntity;
import com.practice.online_diagnost.repositories.entities.RoleEntity;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.domains.RoleDomain;

import java.util.List;
import java.util.stream.Collectors;

public class DiseaseEntityBuilder {
    public DiseaseEntity create(DiseaseDomain diseaseDomain) {
        return DiseaseEntity.builder()
                .id(diseaseDomain.getId())
                .name(diseaseDomain.getName())
                .symptoms(new SymptomEntityBuilder().create(diseaseDomain.getSymptoms()))
                .patients(new PatientEntityBuilder().create(diseaseDomain.getPatients()))
                .createdDate(diseaseDomain.getCreatedDate())
                .build();
    }

    public List<DiseaseEntity> create(List<DiseaseDomain> diseaseDomainList) {
        return diseaseDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
