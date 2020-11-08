package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.DiagnosterResponseModel;
import com.practice.online_diagnost.repositories.entities.DiagnosterResponseEntity;
import com.practice.online_diagnost.services.domains.DiagnosterResponseDomain;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosterResponseDomainBuilder {
    public DiagnosterResponseDomain create(DiagnosterResponseModel diagnosterResponseModel) {
        return DiagnosterResponseDomain.builder()
                .diseaseName(diagnosterResponseModel.getDiseaseName())
                .probability(diagnosterResponseModel.getProbability())
                .patientsId(diagnosterResponseModel.getPatientsId())
                .symptoms(diagnosterResponseModel.getSymptoms())
                .build();
    }

    public List<DiagnosterResponseDomain> create2(List<DiagnosterResponseModel> assignmentRequestModelList) {
        return assignmentRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public DiagnosterResponseDomain create(DiagnosterResponseEntity diagnosterResponseEntity) {
        return DiagnosterResponseDomain.builder()
                .diseaseName(diagnosterResponseEntity.getDiseaseName())
                .probability(diagnosterResponseEntity.getProbability())
                .patientsId(diagnosterResponseEntity.getPatientsId())
                .symptoms(diagnosterResponseEntity.getSymptoms())
                .createdDate(diagnosterResponseEntity.getCreatedDate())
                .build();
    }

    public List<DiagnosterResponseDomain> create(List<DiagnosterResponseEntity> assignmentEntityList) {
        return assignmentEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
