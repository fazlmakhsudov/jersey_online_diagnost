package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.MedicEntity;
import com.practice.online_diagnost.services.domains.MedicDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedicEntityBuilder {
    public MedicEntity create(MedicDomain medicDomain) {
        return MedicEntity.builder()
                .id(medicDomain.getId())
                .specialization(Objects.isNull(medicDomain.getSpecialization()) ?
                        "" : medicDomain.getSpecialization())
                .build();
    }

    public List<MedicEntity> create(List<MedicDomain> medicDomainList) {
        return medicDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
