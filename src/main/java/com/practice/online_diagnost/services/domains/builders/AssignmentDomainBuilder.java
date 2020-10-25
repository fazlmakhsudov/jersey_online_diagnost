package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.AssignmentRequestModel;
import com.practice.online_diagnost.repositories.entities.AssignmentEntity;
import com.practice.online_diagnost.services.domains.AssignmentDomain;

import java.util.List;
import java.util.stream.Collectors;

public class AssignmentDomainBuilder {
    public AssignmentDomain create(AssignmentRequestModel assignmentModel) {
        return AssignmentDomain.builder()
                .id(assignmentModel.getId())
                .name(assignmentModel.getName())
                .diagnosesId(assignmentModel.getDiagnosesId())
                .medicsId(assignmentModel.getMedicsId())
                .build();
    }

    public List<AssignmentDomain> create2(List<AssignmentRequestModel> assignmentRequestModelList) {
        return assignmentRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public AssignmentDomain create(AssignmentEntity assignmentEntity) {
        return AssignmentDomain.builder()
                .id(assignmentEntity.getId())
                .name(assignmentEntity.getName())
                .diagnosesId(assignmentEntity.getDiagnosesId())
                .medicsId(assignmentEntity.getMedicsId())
                .createdDate(assignmentEntity.getCreatedDate())
                .updatedDate(assignmentEntity.getUpdatedDate())
                .build();
    }

    public List<AssignmentDomain> create(List<AssignmentEntity> assignmentEntityList) {
        return assignmentEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
