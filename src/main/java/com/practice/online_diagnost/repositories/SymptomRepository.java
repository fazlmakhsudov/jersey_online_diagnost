package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.SymptomEntity;

import java.sql.Connection;
import java.util.List;

public interface SymptomRepository extends BaseRepository<SymptomEntity> {
    SymptomEntity read(String name, Connection con) throws RepositoryException;

    List<SymptomEntity> readForDiagnoses(int diagnosesId, Connection con) throws RepositoryException;

    List<SymptomEntity> readForDiseases(int diseasesId, Connection con) throws RepositoryException;
}
