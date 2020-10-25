package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.PatientEntity;

import java.sql.Connection;
import java.util.List;

public interface PatientRepository extends BaseRepository<PatientEntity> {

    List<PatientEntity> readForDiseases(int diseasesId, Connection con) throws RepositoryException;

}
