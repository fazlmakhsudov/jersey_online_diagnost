package com.practice.online_diagnost.api.models.builders;


import com.practice.online_diagnost.api.models.AssignmentResponseModel;
import com.practice.online_diagnost.services.domains.AssignmentDomain;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AssignmentResponseModelBuilder {
    public AssignmentResponseModel create(AssignmentDomain assignmentDomain) {
        return AssignmentResponseModel.builder()
                .id(assignmentDomain.getId())
                .name(assignmentDomain.getName())
                .diagnosesId(assignmentDomain.getDiagnosesId())
                .medicsId(assignmentDomain.getMedicsId())
                .createdDate(assignmentDomain.getCreatedDate())
                .updatedDate(assignmentDomain.getUpdatedDate())
                .build();
    }

    public List<AssignmentResponseModel> create(List<AssignmentDomain> assignmentDomains) {
        return assignmentDomains.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
