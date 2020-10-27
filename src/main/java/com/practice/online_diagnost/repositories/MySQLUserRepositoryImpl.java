package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.repositories.entities.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySQLUserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = Logger.getLogger("MySQLUserRepository");


    @Override
    public int create(UserEntity user, Connection con) throws RepositoryException {
        final String query = "INSERT INTO users (name, surname, email, password, phone, patients_id, " +
                "medics_id, roles_id, gender, birth_date, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int id = -1;
        try (PreparedStatement statement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getSurname());
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getPassword());
            statement.setString(k++, user.getPhone());
            statement.setInt(k++, user.getPatientsId());
            statement.setInt(k++, user.getMedicsId());
            statement.setInt(k++, user.getRolesId());
            statement.setString(k++, user.getGender());
            statement.setDate(k++, user.getBirthdate());
            statement.setString(k, user.getLocation());
            System.out.println(statement);
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs != null && rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_INSERT_USER);
            throw new RepositoryException(Messages.ERR_CANNOT_INSERT_USER, e);
        }
        return id;
    }

    @Override
    public UserEntity read(int id, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users WHERE id = ?;";
        UserEntity user = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(resultSet.getString(Columns.EMAIL))
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, e);
        }
        return user;
    }

    @Override
    public UserEntity read(String email, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users WHERE email = ?;";
        UserEntity user = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(email)
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL);
            throw new RepositoryException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, e);
        }
        return user;
    }

    @Override
    public UserEntity readForPatients(int patientsId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users WHERE patients_id = ?;";
        UserEntity user = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, patientsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(resultSet.getString(Columns.EMAIL))
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(patientsId)
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_USERS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_USERS_WITH_LIMITATION, e);
        }
        return user;
    }

    @Override
    public UserEntity readForMedics(int medicsId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users WHERE medics_id = ?;";
        UserEntity user = null;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, medicsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(resultSet.getString(Columns.EMAIL))
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .medicsId(medicsId)
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_USERS_WITH_LIMITATION);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_USERS_WITH_LIMITATION, e);
        }
        return user;
    }

    @Override
    public List<UserEntity> readForRoles(int rolesId, Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users where roles_id = ?;";
        List<UserEntity> userList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, rolesId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    UserEntity user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(resultSet.getString(Columns.EMAIL))
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_USERS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_USERS, e);
        }
        return userList;
    }

    @Override
    public boolean update(UserEntity user, Connection con) throws RepositoryException {
        final String query = "UPDATE users SET name = ?, surname = ?, email = ?, password = ?, phone = ?, " +
                "patients_id = ?, medics_id = ?, roles_id = ?, gender = ?, birth_date = ?, " +
                "location = ?   WHERE id = ?;";
        boolean rowUpdated;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            int k = 1;
            statement.setString(k++, user.getName());
            statement.setString(k++, user.getSurname());
            statement.setString(k++, user.getEmail());
            statement.setString(k++, user.getPassword());
            statement.setString(k++, user.getPhone());
            statement.setInt(k++, user.getPatientsId());
            statement.setInt(k++, user.getMedicsId());
            statement.setInt(k++, user.getRolesId());
            statement.setString(k++, user.getGender());
            statement.setDate(k++, user.getBirthdate());
            statement.setString(k++, user.getLocation());
            statement.setInt(k, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_UPDATE_USER);
            throw new RepositoryException(Messages.ERR_CANNOT_UPDATE_USER, e);
        }
        return rowUpdated;
    }

    @Override
    public boolean delete(int id, Connection con) throws RepositoryException {
        final String query = "DELETE FROM users where id = ?;";
        boolean rowDeleted;
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_DELETE_USER);
            throw new RepositoryException(Messages.ERR_CANNOT_DELETE_USER, e);
        }
        return rowDeleted;
    }

    @Override
    public List<UserEntity> readAll(Connection con) throws RepositoryException {
        final String query = "SELECT * FROM users;";
        List<UserEntity> userList = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(Columns.ENTITY_ID);
                    String name = resultSet.getString(Columns.ENTITY_NAME);
                    UserEntity user = UserEntity.builder()
                            .id(id)
                            .name(name)
                            .surname(resultSet.getString(Columns.SURNAME))
                            .email(resultSet.getString(Columns.EMAIL))
                            .password(resultSet.getString(Columns.PASSWORD))
                            .phone(resultSet.getString(Columns.PHONE))
                            .patientsId(resultSet.getInt(Columns.PATIENTS_ID))
                            .medicsId(resultSet.getInt(Columns.MEDICS_ID))
                            .rolesId(resultSet.getInt(Columns.ROLES_ID))
                            .gender(resultSet.getString(Columns.GENDER))
                            .birthdate(resultSet.getDate(Columns.BIRTHDATE))
                            .location(resultSet.getString(Columns.LOCATION))
                            .createdDate(resultSet.getDate(Columns.CREATED_DATE))
                            .updatedDate(resultSet.getDate(Columns.UPDATED_DATE))
                            .build();
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_READ_ALL_USERS);
            throw new RepositoryException(Messages.ERR_CANNOT_READ_ALL_USERS, e);
        }
        return userList;
    }

}
