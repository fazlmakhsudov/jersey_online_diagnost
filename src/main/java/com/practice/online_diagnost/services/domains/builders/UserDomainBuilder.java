package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.UserRequestModel;
import com.practice.online_diagnost.repositories.entities.UserEntity;
import com.practice.online_diagnost.services.domains.UserDomain;

import java.util.List;
import java.util.stream.Collectors;

public class UserDomainBuilder {
    public UserDomain create(UserRequestModel userModel) {
        return UserDomain.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .phone(userModel.getPhone())
                .medicsId(userModel.getMedicsId())
                .patientsId(userModel.getPatientsId())
                .rolesId(userModel.getRolesId())
                .build();
    }

    public List<UserDomain> create2(List<UserRequestModel> userRequestModelList) {
        return userRequestModelList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    public UserDomain create(UserEntity userEntity) {
        return UserDomain.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phone(userEntity.getPhone())
                .medicsId(userEntity.getMedicsId())
                .patientsId(userEntity.getPatientsId())
                .createdDate(userEntity.getCreatedDate())
                .updatedDate(userEntity.getUpdatedDate())
                .rolesId(userEntity.getRolesId())
                .build();
    }

    public List<UserDomain> create(List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
