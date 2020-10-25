package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.AssignmentEntity;

import java.sql.Connection;
import java.util.List;

public interface AssignmentRepository extends BaseRepository<AssignmentEntity> {
    AssignmentEntity read(String name, Connection con) throws RepositoryException;

    List<AssignmentEntity> readForDiagnoses(int diagnosesId, Connection con) throws RepositoryException;

    List<AssignmentEntity> readForMedics(int medicsId, Connection con) throws RepositoryException;
}
