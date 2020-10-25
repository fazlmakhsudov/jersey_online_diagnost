package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.TreatmentHistoryEntity;

import java.sql.Connection;

public interface TreatmentHistoryRepository extends BaseRepository<TreatmentHistoryEntity> {

    TreatmentHistoryEntity readForPatients(int patientsId, Connection con) throws RepositoryException;

}
