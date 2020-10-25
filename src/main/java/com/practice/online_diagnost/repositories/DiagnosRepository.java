package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;

import java.sql.Connection;
import java.util.List;

public interface DiagnosRepository extends BaseRepository<DiagnosEntity> {
    DiagnosEntity read(String name, Connection con) throws RepositoryException;

    List<DiagnosEntity> readForTreatmentHistories(int treatmentHistoriesId, Connection con) throws RepositoryException;

}
