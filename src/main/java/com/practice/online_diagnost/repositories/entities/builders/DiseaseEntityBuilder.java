package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.DiseaseEntity;
import com.practice.online_diagnost.services.domains.DiseaseDomain;

import java.util.List;
import java.util.stream.Collectors;

public class DiseaseEntityBuilder {
    public DiseaseEntity create(DiseaseDomain diseaseDomain) {
        return DiseaseEntity.builder()
                .id(diseaseDomain.getId())
                .name(diseaseDomain.getName())
                .createdDate(diseaseDomain.getCreatedDate())
                .build();
    }

    public List<DiseaseEntity> create(List<DiseaseDomain> diseaseDomainList) {
        return diseaseDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
