package com.practice.online_diagnost.repositories;

import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.UserEntity;

import java.sql.Connection;
import java.util.List;

public interface UserRepository extends BaseRepository<UserEntity> {
    UserEntity read(String email, Connection con) throws RepositoryException;

    UserEntity readForPatients(int patientsId, Connection con) throws RepositoryException;

    UserEntity readForMedics(int medicsId, Connection con) throws RepositoryException;

    List<UserEntity> readForRoles(int rolesId, Connection con) throws RepositoryException;
}
