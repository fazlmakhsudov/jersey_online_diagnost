package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.QuestionaryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLQuestionaryRepositoryImpl implements QuestionaryRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLQuestionaryRepository");

  
    @Override
    public int create(QuestionaryEntity questionary, Connection con) throws RepositoryException {
        final String query = "INSERT INTO questionaries (name) VALUES (?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, questionary.getName());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_QUESTIONARY);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_QUESTIONARY, e);
        }
        return rowInserted;
    }

    @Override
    public QuestionaryEntity read(int id, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM questionaries WHERE id = ?;";
        QuestionaryEntity questionary = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    questionary = QuestionaryEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_QUESTIONARY_BY_ID);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_QUESTIONARY_BY_ID, e);
        }
        return questionary;
    }

    @Override
    public QuestionaryEntity read(String name, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM questionaries WHERE name = ?;";
        QuestionaryEntity questionary = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    questionary = QuestionaryEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_QUESTIONARY_BY_NAME);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_QUESTIONARY_BY_NAME, e);
        }
        return questionary;
    }

    @Override
    public boolean update(QuestionaryEntity questionary, Connection con) throws RepositoryException {
        final String query = "UPDATE questionaries SET name = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, questionary.getName());
            statement.setInt(2, questionary.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_QUESTIONARY);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_QUESTIONARY, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM questionaries where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_QUESTIONARY);
            throw  new RepositoryException(Messages.ERR_CANNOT_DELETE_QUESTIONARY, e);
        }
        return rowDeleted;
    }

    @Override
    public List<QuestionaryEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM questionaries;";
        List<QuestionaryEntity> questionaryList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    QuestionaryEntity questionary = QuestionaryEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    questionaryList.add(questionary);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_QUESTIONARIES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_QUESTIONARIES, e);
        }
        return questionaryList;
    }
}
