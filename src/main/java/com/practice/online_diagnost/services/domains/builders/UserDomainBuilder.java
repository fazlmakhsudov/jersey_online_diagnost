package com.practice.online_diagnost.services.domains.builders;

import com.practice.online_diagnost.api.models.UserRequestModel;
import com.practice.online_diagnost.repositories.entities.UserEntity;
import com.practice.online_diagnost.services.domains.UserDomain;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDomainBuilder {
    public UserDomain create(UserRequestModel userModel) {
        return UserDomain.builder()
                .id(userModel.getId())
                .name(Objects.isNull(userModel.getName()) ?
                        "" : userModel.getName())
                .surname(Objects.isNull(userModel.getSurname()) ?
                        "" :userModel.getSurname())
                .email(Objects.isNull(userModel.getEmail()) ?
                        "" :userModel.getEmail())
                .password(Objects.isNull(userModel.getPassword()) ?
                        "" :userModel.getPassword())
                .phone(Objects.isNull(userModel.getPhone()) ?
                        "" :userModel.getPhone())
                .medicsId(userModel.getMedicsId())
                .patientsId(userModel.getPatientsId())
                .rolesId(userModel.getRolesId())
                .gender(!Objects.isNull(userModel.getGender()) && userModel.getGender().equalsIgnoreCase("female") ?
                        "female" : "male")
                .location(Objects.isNull(userModel.getLocation()) ?
                        "" :userModel.getLocation())
                .birthdate(Objects.isNull(userModel.getBirthdate()) ?
                        Date.valueOf("2012-12-12") : userModel.getBirthdate())
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
                .rolesId(userEntity.getRolesId())
                .gender(userEntity.getGender())
                .location(userEntity.getLocation())
                .birthdate(userEntity.getBirthdate())
                .createdDate(userEntity.getCreatedDate())
                .updatedDate(userEntity.getUpdatedDate())
                .build();
    }

    public List<UserDomain> create(List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
