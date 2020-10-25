package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.QuestionEntity;

import java.sql.Connection;
import java.util.List;

public interface QuestionRepository extends BaseRepository<QuestionEntity> {
    QuestionEntity read(String name, Connection con) throws RepositoryException;

    List<QuestionEntity> readForQuestionaries(int questionariesId, Connection con) throws RepositoryException;

}
