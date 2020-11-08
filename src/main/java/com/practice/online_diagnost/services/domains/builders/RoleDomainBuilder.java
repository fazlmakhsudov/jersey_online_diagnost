package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.RoleRequestModel;
import com.practice.online_diagnost.repositories.entities.RoleEntity;
import com.practice.online_diagnost.services.domains.RoleDomain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoleDomainBuilder {
    public RoleDomain create(RoleRequestModel roleModel) {
        return RoleDomain.builder()
                .id(roleModel.getId())
                .name(Objects.isNull(roleModel.getName()) ?
                        "" : roleModel.getName())
                .build();
    }

    public List<RoleDomain> create2(List<RoleRequestModel> roleRequestModelList) {
        return roleRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public RoleDomain create(RoleEntity roleEntity) {
        return RoleDomain.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }

    public List<RoleDomain> create(List<RoleEntity> roleEntityList) {
        return roleEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
