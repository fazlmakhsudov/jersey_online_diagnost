package com.practice.online_diagnost.repositories;

import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosterResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlDiagnosterResponseRepository {
    private static final Logger LOGGER =
            Logger.getLogger(MySqlDiagnosterResponseRepository.class.getSimpleName());

    public int create(DiagnosterResponseEntity diagnosterResponseEntity, Connection con, String tableName) throws RepositoryException {
        final String query = String.format("INSERT INTO %s (disease_name, probability, patients_id, symptoms) " +
                "VALUES (?,?,?,?);", tableName);

        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, diagnosterResponseEntity.getDiseaseName());
            statement.setDouble(k++, diagnosterResponseEntity.getProbability());
            statement.setInt(k++, diagnosterResponseEntity.getPatientsId());
            statement.setString(k, diagnosterResponseEntity.getSymptoms());

            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_DIAGNOSTER_RESPONSE);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_DIAGNOSTER_RESPONSE, e);
        }
        return rowInserted;
    }

    public List<DiagnosterResponseEntity> readAll(Connection con, String tableName) throws RepositoryException {
        final String query = String.format("SELECT * FROM %s;", tableName);
        List<DiagnosterResponseEntity> diagnosterResponseEntities = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DiagnosterResponseEntity diagnosterResponseEntity =
                            DiagnosterResponseEntity.builder()
                                    .diseaseName(resultSet.getString(Columns.DISEASE_NAME))
                                    .probability(resultSet.getDouble(Columns.PROBABILITY))
                                    .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                                    .symptoms(resultSet.getString(Columns.SYMPTOMS))
                                    .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                                    .build();
                    diagnosterResponseEntities.add(diagnosterResponseEntity);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES, e);
        }
        return diagnosterResponseEntities;
    }

    public List<DiagnosterResponseEntity> readAllForPatient(Connection con, String tableName, int patientsId) throws RepositoryException {
        final String query = String.format("SELECT * FROM %s where patients_id = ?;", tableName);
        List<DiagnosterResponseEntity> diagnosterResponseEntities = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, patientsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DiagnosterResponseEntity diagnosterResponseEntity =
                            DiagnosterResponseEntity.builder()
                                    .diseaseName(resultSet.getString(Columns.DISEASE_NAME))
                                    .probability(resultSet.getDouble(Columns.PROBABILITY))
                                    .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                                    .symptoms(resultSet.getString(Columns.SYMPTOMS))
                                    .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                                    .build();
                    diagnosterResponseEntities.add(diagnosterResponseEntity);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES, e);
        }
        return diagnosterResponseEntities;
    }
}
