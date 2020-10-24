package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;
import com.practice.online_diagnost.repositories.entities.QuestionaryEntity;

import java.sql.Connection;
import java.util.List;

public interface QuestionaryRepository extends BaseRepository<QuestionaryEntity> {
    QuestionaryEntity read(String name, Connection con) throws RepositoryException;
}
