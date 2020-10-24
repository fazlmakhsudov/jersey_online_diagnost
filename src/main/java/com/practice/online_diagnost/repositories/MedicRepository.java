package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.repositories.entities.MedicEntity;

import java.sql.Connection;
import java.util.List;

public interface MedicRepository extends BaseRepository<MedicEntity> {
    MedicEntity read(String specitalization, Connection con) throws RepositoryException;
}
