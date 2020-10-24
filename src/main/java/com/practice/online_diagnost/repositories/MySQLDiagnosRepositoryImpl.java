package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiagnosEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLDiagnosRepositoryImpl implements DiagnosRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLDiagnosRepository");


    @Override
    public int create(DiagnosEntity diagnos, Connection con) throws RepositoryException {
        final String query = "INSERT INTO diagnoses (name, treatment_histories_id) VALUES (?,?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, diagnos.getName());
            statement.setInt(2, diagnos.getTreatmentHistoriesId());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_DIAGNOS);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_DIAGNOS, e);
        }
        return rowInserted;
    }

    @Override
    public DiagnosEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM diagnoses WHERE id = ?;";
        DiagnosEntity diagnos = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    diagnos = DiagnosEntity.builder()
                            .id(id)
                            .name(name)
                            .treatmentHistoriesId(resultSet.getInt(Columns.TREATMENT_HISTORIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_DIAGNOS_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_DIAGNOS_BY_ID, e);
        }
        return diagnos;
    }

    @Override
    public DiagnosEntity read(String name, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM diagnoses WHERE name = ?;";
        DiagnosEntity diagnos = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    diagnos = DiagnosEntity.builder()
                            .id(id)
                            .name(name)
                            .treatmentHistoriesId(resultSet.getInt(Columns.TREATMENT_HISTORIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_DIAGNOS_BY_NAME);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_DIAGNOS_BY_NAME, e);
        }
        return diagnos;
    }

    @Override
    public List<DiagnosEntity> readForTreatmentHistories(int treatmentHistoriesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM diagnoses where treatment_histories_id = ?;";
        List<DiagnosEntity> diagnosList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, treatmentHistoriesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    DiagnosEntity diagnos = DiagnosEntity.builder()
                            .id(id)
                            .name(name)
                            .treatmentHistoriesId(resultSet.getInt(Columns.TREATMENT_HISTORIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    diagnosList.add(diagnos);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_DIAGNOSES_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_DIAGNOSES_WITH_LIMITATION, e);
        }
        return diagnosList;
    }


    @Override
    public boolean update(DiagnosEntity diagnos, Connection con) throws RepositoryException {
        final String query = "UPDATE diagnoses SET name = ?, treatment_histories_id = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, diagnos.getName());
            statement.setInt(k++, diagnos.getTreatmentHistoriesId());
            statement.setInt(k, diagnos.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_DIAGNOS);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_DIAGNOS, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM diagnoses where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_DIAGNOS);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_DIAGNOS, e);
        }
        return rowDeleted;
    }

    @Override
    public List<DiagnosEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM diagnoses;";
        List<DiagnosEntity> diagnosList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    DiagnosEntity diagnos = DiagnosEntity.builder()
                            .id(id)
                            .name(name)
                            .treatmentHistoriesId(resultSet.getInt(Columns.TREATMENT_HISTORIES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    diagnosList.add(diagnos);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_DIAGNOSES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_DIAGNOSES, e);
        }
        return diagnosList;
    }
}
