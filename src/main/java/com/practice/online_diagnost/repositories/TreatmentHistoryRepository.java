package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.repositories.entities.TreatmentHistoryEntity;

import java.sql.Connection;
import java.util.List;

public interface TreatmentHistoryRepository extends BaseRepository<TreatmentHistoryEntity> {

    List<TreatmentHistoryEntity> readForPatients(int patientsId, Connection con) throws RepositoryException;

}
