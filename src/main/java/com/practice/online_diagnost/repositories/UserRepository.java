package com.practice.online_diagnost.repositories;

import com.practice.online_diagnost.repositories.entities.UserEntity;

import java.util.List;

public interface UserRepository extends BaseRepository<UserEntity> {
    UserEntity read(String email);

    UserEntity readForPatients(int patientsId);

    UserEntity readForMedics(int medicsId);

    List<UserEntity> readForRoles(int rolesId);
}
