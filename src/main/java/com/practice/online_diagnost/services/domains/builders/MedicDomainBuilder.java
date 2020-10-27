package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.MedicRequestModel;
import com.practice.online_diagnost.repositories.entities.MedicEntity;
import com.practice.online_diagnost.services.domains.MedicDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MedicDomainBuilder {
    public MedicDomain create(MedicRequestModel medicModel) {
        return MedicDomain.builder()
                .id(medicModel.getId())
                .specialization(medicModel.getSpecialization())
//                .assignments(Objects.isNull(medicModel.getAssignments()) ? null
//                        : new AssignmentDomainBuilder().create2(medicModel.getAssignments()))
//                .userDomain(Objects.isNull(medicModel.getUser()) ? null
//                        : new UserDomainBuilder().create(medicModel.getUser()))
                .build();
    }

    public List<MedicDomain> create2(List<MedicRequestModel> medicRequestModelList) {
        return medicRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public MedicDomain create(MedicEntity medicEntity) {
        return MedicDomain.builder()
                .id(medicEntity.getId())
                .specialization(medicEntity.getSpecialization())
                .assignments(Objects.isNull(medicEntity.getAssignments()) ? null
                        : new AssignmentDomainBuilder().create(medicEntity.getAssignments()))
                .userDomain(Objects.isNull(medicEntity.getUserEntity()) ? null
                        : new UserDomainBuilder().create(medicEntity.getUserEntity()))
                .build();
    }

    public List<MedicDomain> create(List<MedicEntity> medicEntityList) {
        return medicEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
