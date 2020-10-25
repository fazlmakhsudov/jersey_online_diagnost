package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.RoleEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLRoleRepositoryImpl implements RoleRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLRoleRepository");


    @Override
    public int create(RoleEntity role, Connection con) throws RepositoryException {
        final String query = "INSERT INTO roles (name) VALUES (?);";
        int rowInserted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, role.getName());
            rowInserted = statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_ROLE);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_ROLE, e);
        }
        return rowInserted;
    }

    @Override
    public RoleEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM roles WHERE id = ?;";
        RoleEntity role = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    role = RoleEntity.builder()
                            .id(id)
                            .name(name)
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_ROLE_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_ROLE_BY_ID, e);
        }
        return role;
    }

    @Override
    public RoleEntity read(String name, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM roles WHERE name = ?;";
        RoleEntity role = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    role = RoleEntity.builder()
                            .id(id)
                            .name(name)
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_ROLE_BY_NAME);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_ROLE_BY_NAME, e);
        }
        return role;
    }

    @Override
    public boolean update(RoleEntity role, Connection con) throws RepositoryException {
        final String query = "UPDATE roles SET name = ? WHERE id = ?;";
        boolean rowUpdated = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_ROLE);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_ROLE, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM roles where id = ?;";
        boolean rowDeleted = false;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_ROLE);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_ROLE, e);
        }
        return rowDeleted;
    }

    @Override
    public List<RoleEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM roles;";
        List<RoleEntity> roleList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    RoleEntity role = RoleEntity.builder()
                            .id(id)
                            .name(name)
                            .build();
                    roleList.add(role);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_ROLES);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_ROLES, e);
        }
        return roleList;
    }
}
