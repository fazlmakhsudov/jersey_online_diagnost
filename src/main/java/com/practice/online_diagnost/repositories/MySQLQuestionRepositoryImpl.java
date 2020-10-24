package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.QuestionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLQuestionRepositoryImpl implements QuestionRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLQuestionRepository");

  
    @Override
    public int create(QuestionEntity question, Connection con) throws RepositoryException {
        final String query = "INSERT INTO questions (name, answer, questionaries_id) VALUES (?,?,?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, question.getName());
            statement.setString(2, question.getAnswer());
            statement.setInt(3, question.getQuestionariesId());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_QUESTION);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_QUESTION, e);
        }
        return rowInserted;
    }

    @Override
    public QuestionEntity read(int id, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM questions WHERE id = ?;";
        QuestionEntity question = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    question = QuestionEntity.builder()
                            .id(id)
                            .name(name)
                            .answer(resultSet.getString(Columns.ANSWER))
                            .questionariesId(resultSet.getInt(Columns.QUECTIONARIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_ID);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_ID, e);
        }
        return question;
    }

    @Override
    public QuestionEntity read(String name, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM questions WHERE name = ?;";
        QuestionEntity question = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    question = QuestionEntity.builder()
                            .id(id)
                            .name(name)
                            .answer(resultSet.getString(Columns.ANSWER))
                            .questionariesId(resultSet.getInt(Columns.QUECTIONARIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_NAME);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_QUESTION_BY_NAME, e);
        }
        return question;
    }

    @Override
    public List<QuestionEntity> readForQuestionaries(int questionariesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM questions where questionaries_id = ?;";
        List<QuestionEntity> questionList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, questionariesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    QuestionEntity question = QuestionEntity.builder()
                            .id(id)
                            .name(name)
                            .answer(resultSet.getString(Columns.ANSWER))
                            .questionariesId(resultSet.getInt(Columns.QUECTIONARIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    questionList.add(question);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_QUESTIONS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_QUESTIONS_WITH_LIMITATION, e);
        }
        return questionList;
    }



    @Override
    public boolean update(QuestionEntity question, Connection con) throws RepositoryException {
        final String query = "UPDATE questions SET name = ?, answer = ?, questionaries_id = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, question.getName());
            statement.setString(k++, question.getAnswer());
            statement.setInt(k++, question.getQuestionariesId());
            statement.setInt(k, question.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_QUESTION);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_QUESTION, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM questions where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_QUESTION);
            throw  new RepositoryException(Messages.ERR_CANNOT_DELETE_QUESTION, e);
        }
        return rowDeleted;
    }

    @Override
    public List<QuestionEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM questions;";
        List<QuestionEntity> questionList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    QuestionEntity question = QuestionEntity.builder()
                            .id(id)
                            .name(name)
                            .answer(resultSet.getString(Columns.ANSWER))
                            .questionariesId(resultSet.getInt(Columns.QUECTIONARIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    questionList.add(question);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_QUESTIONS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_QUESTIONS, e);
        }
        return questionList;
    }
}
