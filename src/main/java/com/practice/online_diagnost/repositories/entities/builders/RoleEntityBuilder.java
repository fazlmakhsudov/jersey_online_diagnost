package com.practice.online_diagnost.repositories.entities.builders;


import com.practice.online_diagnost.repositories.entities.QuestionEntity;
import com.practice.online_diagnost.repositories.entities.RoleEntity;
import com.practice.online_diagnost.services.domains.QuestionDomain;
import com.practice.online_diagnost.services.domains.RoleDomain;

import java.util.List;
import java.util.stream.Collectors;

public class RoleEntityBuilder {
    public RoleEntity create(RoleDomain roleDomain) {
        return RoleEntity.builder()
                .id(roleDomain.getId())
                .name(roleDomain.getName())
                .build();
    }

    public List<RoleEntity> create(List<RoleDomain> roleDomainList) {
        return roleDomainList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
