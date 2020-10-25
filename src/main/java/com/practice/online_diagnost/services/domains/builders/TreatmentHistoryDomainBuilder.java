package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.TreatmentHistoryRequestModel;
import com.practice.online_diagnost.repositories.entities.TreatmentHistoryEntity;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreatmentHistoryDomainBuilder {
    public TreatmentHistoryDomain create(TreatmentHistoryRequestModel treatmentHistoryModel) {
        return TreatmentHistoryDomain.builder()
                .id(treatmentHistoryModel.getId())
                .diagnoses(Objects.isNull(treatmentHistoryModel.getDiagnoses()) ? null
                        : new DiagnosDomainBuilder().create2(treatmentHistoryModel.getDiagnoses()))
                .patientsId(treatmentHistoryModel.getPatientsId())

                .build();
    }

    public List<TreatmentHistoryDomain> create2(List<TreatmentHistoryRequestModel> treatmentHistoryRequestModelList) {
        return treatmentHistoryRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public TreatmentHistoryDomain create(TreatmentHistoryEntity treatmentHistoryEntity) {
        return TreatmentHistoryDomain.builder()
                .id(treatmentHistoryEntity.getId())
                .diagnoses(Objects.isNull(treatmentHistoryEntity.getDiagnoses()) ? null
                        : new DiagnosDomainBuilder().create(treatmentHistoryEntity.getDiagnoses()))
                .patientsId(treatmentHistoryEntity.getPatientsId())
                .createdDate(treatmentHistoryEntity.getCreatedDate())
                .updatedDate(treatmentHistoryEntity.getUpdatedDate())
                .build();
    }

    public List<TreatmentHistoryDomain> create(List<TreatmentHistoryEntity> treatmentHistoryEntityList) {
        return treatmentHistoryEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
