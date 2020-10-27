package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.DiseaseRequestModel;
import com.practice.online_diagnost.repositories.entities.DiseaseEntity;
import com.practice.online_diagnost.services.domains.DiseaseDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiseaseDomainBuilder {
    public DiseaseDomain create(DiseaseRequestModel diseaseModel) {
        return DiseaseDomain.builder()
                .id(diseaseModel.getId())
                .name(diseaseModel.getName())
//                .symptoms(Objects.isNull(diseaseModel.getSymptoms()) ? null
//                        : new SymptomDomainBuilder().create2(diseaseModel.getSymptoms()))
//                .patients(Objects.isNull(diseaseModel.getPatients()) ? null
//                        : new PatientDomainBuilder().create2(diseaseModel.getPatients()))
                .build();
    }

    public List<DiseaseDomain> create2(List<DiseaseRequestModel> diseaseRequestModelList) {
        return diseaseRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public DiseaseDomain create(DiseaseEntity diseaseEntity) {
        return DiseaseDomain.builder()
                .id(diseaseEntity.getId())
                .name(diseaseEntity.getName())
                .symptoms(Objects.isNull(diseaseEntity.getSymptoms()) ? null
                        : new SymptomDomainBuilder().create(diseaseEntity.getSymptoms()))
                .patients(Objects.isNull(diseaseEntity.getPatients()) ? null
                        : new PatientDomainBuilder().create(diseaseEntity.getPatients()))
                .createdDate(diseaseEntity.getCreatedDate())
                .build();
    }

    public List<DiseaseDomain> create(List<DiseaseEntity> diseaseEntityList) {
        return diseaseEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
