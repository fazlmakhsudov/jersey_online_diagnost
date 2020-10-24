package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.AssignmentEntity;
import com.practice.online_diagnost.services.domains.AssignmentDomain;

import java.util.List;
import java.util.stream.Collectors;

public class AssignmentEntityBuilder {
    public AssignmentEntity create(AssignmentDomain assignmentDomain) {
        return AssignmentEntity.builder()
                .id(assignmentDomain.getId())
                .name(assignmentDomain.getName())
                .medicsId(assignmentDomain.getMedicsId())
                .diagnosesId(assignmentDomain.getDiagnosesId())
                .createdDate(assignmentDomain.getCreatedDate())
                .updatedDate(assignmentDomain.getUpdatedDate())
                .build();
    }

    public List<AssignmentEntity> create(List<AssignmentDomain> assignmentDomainList) {
        return assignmentDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
