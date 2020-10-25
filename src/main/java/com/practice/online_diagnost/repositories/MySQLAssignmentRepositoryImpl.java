package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.AssignmentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLAssignmentRepositoryImpl implements AssignmentRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLAssignmentRepository");


    @Override
    public int create(AssignmentEntity assignment, Connection con) throws RepositoryException {
        final String query = "INSERT INTO assignments (name, diagnoses_id, medics_id) VALUES (?,?,?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, assignment.getName());
            statement.setInt(2, assignment.getDiagnosesId());
            statement.setInt(3, assignment.getMedicsId());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_ASSIGNMENT);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_ASSIGNMENT, e);
        }
        return rowInserted;
    }

    @Override
    public AssignmentEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM assignments WHERE id = ?;";
        AssignmentEntity assignment = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    assignment = AssignmentEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_ID, e);
        }
        return assignment;
    }

    @Override
    public AssignmentEntity read(String name, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM assignments WHERE name = ?;";
        AssignmentEntity assignment = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    assignment = AssignmentEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME, e);
        }
        return assignment;
    }

    @Override
    public List<AssignmentEntity> readForDiagnoses(int diagnosesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM assignments where diagnoses_id = ?;";
        List<AssignmentEntity> assignmentList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, diagnosesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    AssignmentEntity assignment = AssignmentEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    assignmentList.add(assignment);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION, e);
        }
        return assignmentList;
    }

    @Override
    public List<AssignmentEntity> readForMedics(int medicsId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM assignments where medics_id = ?;";
        List<AssignmentEntity> assignmentList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, medicsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    AssignmentEntity assignment = AssignmentEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    assignmentList.add(assignment);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION, e);
        }
        return assignmentList;
    }

    @Override
    public boolean update(AssignmentEntity assignment, Connection con) throws RepositoryException {
        final String query = "UPDATE assignments SET name = ?, diagnoses_id = ?, medics_id = ? WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, assignment.getName());
            statement.setInt(k++, assignment.getDiagnosesId());
            statement.setInt(k++, assignment.getMedicsId());
            statement.setInt(k, assignment.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_ASSIGNMENT);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_ASSIGNMENT, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM assignments where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_ASSIGNMENT);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_ASSIGNMENT, e);
        }
        return rowDeleted;
    }

    @Override
    public List<AssignmentEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM assignments;";
        List<AssignmentEntity> assignmentList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    AssignmentEntity assignment = AssignmentEntity.builder()
                            .id(id)
                            .name(name)
                            .diagnosesId(resultSet.getInt(Columns.DIAGNOSES_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    assignmentList.add(assignment);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_ASSIGNMENTS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_ASSIGNMENTS, e);
        }
        return assignmentList;
    }
}
