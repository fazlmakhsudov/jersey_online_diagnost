package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.TreatmentHistoryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLTreatmentHistoryRepositoryImpl implements TreatmentHistoryRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLTreatmentHistoryRepository");


    @Override
    public int create(TreatmentHistoryEntity treatmentHistory, Connection con) throws RepositoryException {
        final String query = "INSERT INTO treatment_histories (patients_id) VALUES (?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, treatmentHistory.getPatientsId());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_TREATMENTHISTORY);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_TREATMENTHISTORY, e);
        }
        return rowInserted;
    }

    @Override
    public TreatmentHistoryEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM treatment_histories WHERE id = ?;";
        TreatmentHistoryEntity treatmentHistory = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    treatmentHistory = TreatmentHistoryEntity.builder()
                            .id(id)
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID, e);
        }
        return treatmentHistory;
    }

    @Override
    public boolean update(TreatmentHistoryEntity treatmentHistory, Connection con) throws RepositoryException {
        final String query = "UPDATE treatment_histories SET patients_id = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, treatmentHistory.getPatientsId());
            statement.setInt(2, treatmentHistory.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_TREATMENTHISTORY);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_TREATMENTHISTORY, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM treatment_histories where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_TREATMENTHISTORY);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_TREATMENTHISTORY, e);
        }
        return rowDeleted;
    }

    @Override
    public List<TreatmentHistoryEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM treatment_histories;";
        List<TreatmentHistoryEntity> treatmentHistoryList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    TreatmentHistoryEntity treatmentHistory = TreatmentHistoryEntity.builder()
                            .id(id)
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    treatmentHistoryList.add(treatmentHistory);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_TREATMENTHISTORIES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_TREATMENTHISTORIES, e);
        }
        return treatmentHistoryList;
    }

    @Override
    public TreatmentHistoryEntity readForPatients(int patientsId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM treatment_histories where patients_id = ?;";
        TreatmentHistoryEntity treatmentHistory = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, patientsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    treatmentHistory = TreatmentHistoryEntity.builder()
                            .id(resultSet.getInt(Columns.ENTITY_ID))
                            .patientsId(patientsId)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID, e);
        }
        return treatmentHistory;
    }
}
