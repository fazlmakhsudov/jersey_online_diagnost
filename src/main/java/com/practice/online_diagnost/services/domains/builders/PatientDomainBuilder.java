package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.PatientRequestModel;
import com.practice.online_diagnost.repositories.entities.PatientEntity;
import com.practice.online_diagnost.services.domains.PatientDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PatientDomainBuilder {
    public PatientDomain create(PatientRequestModel patientModel) {
        return PatientDomain.builder()
                .id(patientModel.getId())
                .diseasesId(patientModel.getDiseasesId())

                .condition(Objects.isNull(patientModel.getCondition()) ?
                        "" : patientModel.getCondition())
                .build();
    }

    public List<PatientDomain> create2(List<PatientRequestModel> patientRequestModelList) {
        return patientRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public PatientDomain create(PatientEntity patientEntity) {
        return PatientDomain.builder()
                .id(patientEntity.getId())
                .diseasesId(patientEntity.getDiseasesId())
                .condition(patientEntity.getCondition())
                .treatmentHistory(Objects.isNull(patientEntity.getTreatmentHistoryEntity()) ? null
                        : new TreatmentHistoryDomainBuilder().create(patientEntity.getTreatmentHistoryEntity()))
                .userDomain(Objects.isNull(patientEntity.getUserEntity()) ? null
                        : new UserDomainBuilder().create(patientEntity.getUserEntity()))
                .build();
    }

    public List<PatientDomain> create(List<PatientEntity> patientEntityList) {
        return patientEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
