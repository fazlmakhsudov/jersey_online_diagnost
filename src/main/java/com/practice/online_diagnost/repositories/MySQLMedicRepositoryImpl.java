package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.MedicEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLMedicRepositoryImpl implements MedicRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLMedicRepository");

  
    @Override
    public int create(MedicEntity medic, Connection con) throws RepositoryException {
        final String query = "INSERT INTO medics (specialization) VALUES (?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, medic.getSpecialization());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_MEDIC);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_MEDIC, e);
        }
        return rowInserted;
    }

    @Override
    public MedicEntity read(int id, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM medics WHERE id = ?;";
        MedicEntity medic = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medic = MedicEntity.builder()
                            .id(id)
                            .specialization(resultSet.getString(Columns.SPECIALIZATION))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_MEDIC_BY_ID);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_MEDIC_BY_ID, e);
        }
        return medic;
    }

    @Override
    public MedicEntity read(String specialization, Connection con) throws RepositoryException {
        final String query  = "SELECT * FROM medics WHERE specialization = ?;";
        MedicEntity medic = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, specialization);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    medic = MedicEntity.builder()
                            .id(id)
                            .specialization(specialization)
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION);
            throw  new RepositoryException(Messages.ERR_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION, e);
        }
        return medic;
    }

    @Override
    public boolean update(MedicEntity medic, Connection con) throws RepositoryException {
        final String query = "UPDATE medics SET specialization = ? WHERE id = ?;";
        boolean rowUpdated = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, medic.getSpecialization());
            statement.setInt(2, medic.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_MEDIC);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_MEDIC, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM medics where id = ?;";
        boolean rowDeleted = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_MEDIC);
            throw  new RepositoryException(Messages.ERR_CANNOT_DELETE_MEDIC, e);
        }
        return rowDeleted;
    }

    @Override
    public List<MedicEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM medics;";
        List<MedicEntity> medicList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    MedicEntity medic = MedicEntity.builder()
                            .id(id)
                            .specialization(resultSet.getString(Columns.SPECIALIZATION))
                            .build();
                    medicList.add(medic);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_MEDICS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_MEDICS, e);
        }
        return medicList;
    }
}
