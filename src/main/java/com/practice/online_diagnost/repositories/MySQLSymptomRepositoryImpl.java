package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.SymptomEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLSymptomRepositoryImpl implements SymptomRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLSymptomRepository");


    @Override
    public int create(SymptomEntity symptom, Connection con) throws RepositoryException {
        final String query = "INSERT INTO symptoms (name, diagnoses_id, diseases_id) VALUES (?,?,?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, symptom.getName());
            statement.setInt(2, symptom.getDiagnosesId());
            statement.setInt(3, symptom.getDiseasesId());

            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_SYMPTOM);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_SYMPTOM, e);
        }
        return rowInserted;
    }

    @Override
    public SymptomEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM symptoms WHERE id = ?;";
        SymptomEntity symptom = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    symptom = SymptomEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_SYMPTOM_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_SYMPTOM_BY_ID, e);
        }
        return symptom;
    }

    @Override
    public SymptomEntity read(String name, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM symptoms WHERE name = ?;";
        SymptomEntity symptom = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    symptom = SymptomEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_SYMPTOM_BY_NAME);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_SYMPTOM_BY_NAME, e);
        }
        return symptom;
    }

    @Override
    public List<SymptomEntity> readForDiagnoses(int diagnosesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM symptoms where diagnoses_id = ?;";
        List<SymptomEntity> symptomList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, diagnosesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    SymptomEntity symptom = SymptomEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    symptomList.add(symptom);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_SYMPTOMS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_SYMPTOMS_WITH_LIMITATION, e);
        }
        return symptomList;
    }

    @Override
    public List<SymptomEntity> readForDiseases(int diseasesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM symptoms where medics_id = ?;";
        List<SymptomEntity> symptomList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, diseasesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    SymptomEntity symptom = SymptomEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    symptomList.add(symptom);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_SYMPTOMS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_SYMPTOMS_WITH_LIMITATION, e);
        }
        return symptomList;
    }

    @Override
    public boolean update(SymptomEntity symptom, Connection con) throws RepositoryException {
        final String query = "UPDATE symptoms SET name = ?, diagnoses_id = ?, diseases_id = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, symptom.getName());
            statement.setInt(k++, symptom.getDiagnosesId());
            statement.setInt(k++, symptom.getDiseasesId());
            statement.setInt(k, symptom.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_SYMPTOM);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_SYMPTOM, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM symptoms where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_SYMPTOM);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_SYMPTOM, e);
        }
        return rowDeleted;
    }

    @Override
    public List<SymptomEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM symptoms;";
        List<SymptomEntity> symptomList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    SymptomEntity symptom = SymptomEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .diseasesId(resultSet.getInt(Columns.DISEASES_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    symptomList.add(symptom);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_SYMPTOMS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_SYMPTOMS, e);
        }
        return symptomList;
    }
}
