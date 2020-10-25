package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.MedicEntity;

import java.sql.Connection;

public interface MedicRepository extends BaseRepository<MedicEntity> {
    MedicEntity read(String specitalization, Connection con) throws RepositoryException;
}
