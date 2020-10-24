package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.DiseaseEntity;
import com.practice.online_diagnost.repositories.entities.DiseaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLDiseaseRepositoryImpl implements DiseaseRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLDiseaseRepository");

  
    @Override
    public int create(DiseaseEntity disease, Connection con) throws RepositoryException {
        final String query = "INSERT INTO diseases (name) VALUES (?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, disease.getName());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_DISEASE);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_DISEASE, e);
        }
        return rowInserted;
    }

    @Override
    public DiseaseEntity read(int id, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM diseases WHERE id = ?;";
        DiseaseEntity disease = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    disease = DiseaseEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_DISEASE_BY_ID);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_DISEASE_BY_ID, e);
        }
        return disease;
    }

    @Override
    public DiseaseEntity read(String name, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM diseases WHERE name = ?;";
        DiseaseEntity disease = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    disease = DiseaseEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_DISEASE_BY_NAME);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_DISEASE_BY_NAME, e);
        }

        return disease;
    }

    @Override
    public boolean update(DiseaseEntity disease, Connection con) throws RepositoryException {
        final String query = "UPDATE diseases SET name = ? WHERE id = ?;";
        boolean rowUpdated = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, disease.getName());
            statement.setInt(2, disease.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_DISEASE);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_DISEASE, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM diseases where id = ?;";
        boolean rowDeleted = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_DISEASE);
            throw  new RepositoryException(Messages.ERR_CANNOT_DELETE_DISEASE, e);
        }
        return rowDeleted;
    }

    @Override
    public List<DiseaseEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM diseases;";
        List<DiseaseEntity> diseaseList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    DiseaseEntity disease = DiseaseEntity.builder()
                            .id(id)
                            .name(name)
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .build();
                    diseaseList.add(disease);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_DISEASES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_DISEASES, e);
        }
        return diseaseList;
    }
}
