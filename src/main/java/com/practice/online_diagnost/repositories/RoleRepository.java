package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.RoleEntity;

import java.sql.Connection;

public interface RoleRepository extends BaseRepository<RoleEntity> {
    RoleEntity read(String name, Connection con) throws RepositoryException;
}
