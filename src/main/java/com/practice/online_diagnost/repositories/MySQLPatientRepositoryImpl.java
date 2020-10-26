package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.PatientEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLPatientRepositoryImpl implements PatientRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLPatientRepository");


    @Override
    public int create(PatientEntity patient, Connection con) throws RepositoryException {
        final String query = "INSERT INTO patients (diseases_id, condition) VALUES (?, ?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, patient.getDiseasesId());
            statement.setString(2, patient.getCondition());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_PATIENT);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_PATIENT, e);
        }
        return rowInserted;
    }

    @Override
    public PatientEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM patients WHERE id = ?;";
        PatientEntity patient = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = PatientEntity.builder()
                            .id(id)
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .condition(resultSet.getString(Columns.CONDITION))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_PATIENT_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_PATIENT_BY_ID, e);
        }
        return patient;
    }


    @Override
    public boolean update(PatientEntity patient, Connection con) throws RepositoryException {
        final String query = "UPDATE patients SET diseases_id = ?, condition = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, patient.getDiseasesId());
            statement.setString(2, patient.getCondition());
            statement.setInt(3, patient.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_PATIENT);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_PATIENT, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM patients where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_PATIENT);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_PATIENT, e);
        }
        return rowDeleted;
    }

    @Override
    public List<PatientEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM patients;";
        List<PatientEntity> patientList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    PatientEntity patient = PatientEntity.builder()
                            .id(id)
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .condition(resultSet.getString(Columns.CONDITION))
                            .build();
                    patientList.add(patient);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_PATIENTS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_PATIENTS, e);
        }
        return patientList;
    }

    @Override
    public List<PatientEntity> readForDiseases(int diseasesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM patients where diseases_id = ?;";
        List<PatientEntity> patientList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, diseasesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    PatientEntity patient = PatientEntity.builder()
                            .id(id)
                            .diseasesId(diseasesId)
                            .condition(resultSet.getString(Columns.CONDITION))
                            .build();
                    patientList.add(patient);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_PATIENTS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_PATIENTS_WITH_LIMITATION, e);
        }
        return patientList;
    }
}
