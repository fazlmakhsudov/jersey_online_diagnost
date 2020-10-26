package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.UserRepository;
import com.practice.online_diagnost.repositories.entities.UserEntity;
import com.practice.online_diagnost.repositories.entities.builders.UserEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.UserDomain;
import com.practice.online_diagnost.services.domains.builders.UserDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger("UserServiceImpl");
    private final UserRepository userRepository;
    private final UserEntityBuilder userEntityBuilder;
    private final UserDomainBuilder userDomainBuilder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userEntityBuilder = new UserEntityBuilder();
        this.userDomainBuilder = new UserDomainBuilder();

    }

    @Override
    public int add(UserDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = userRepository.create(userEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public UserDomain find(int id) throws ServiceException {
        Connection con = null;
        UserDomain userDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            UserEntity foundUser = userRepository.read(id, con);
            userDomain = Objects.isNull(foundUser) ? null : userDomainBuilder.create(foundUser);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomain;
    }

    @Override
    public UserDomain find(String email) throws ServiceException {
        Connection con = null;
        UserDomain userDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            UserEntity foundUser = userRepository.read(email, con);
            userDomain = Objects.isNull(foundUser) ? null : userDomainBuilder.create(foundUser);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomain;
    }

    @Override
    public UserDomain findForPatients(int patientsId) throws ServiceException {
        Connection con = null;
        UserDomain userDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            userDomain = userDomainBuilder.create(userRepository.readForPatients(patientsId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomain;
    }

    @Override
    public UserDomain findForMedics(int medicsId) throws ServiceException {
        Connection con = null;
        UserDomain userDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            userDomain = userDomainBuilder.create(userRepository.readForMedics(medicsId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomain;
    }

    @Override
    public List<UserDomain> findForRoles(int rolesId) throws ServiceException {
        Connection con = null;
        List<UserDomain> userDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            userDomains = userDomainBuilder.create(userRepository.readForRoles(rolesId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_USERS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomains;
    }

    @Override
    public boolean save(UserDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = userRepository.update(userEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_USER);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_USER, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public boolean remove(int id) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = userRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_USER);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_USER, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<UserDomain> findAll() throws ServiceException {
        Connection con = null;
        List<UserDomain> userDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            userDomains = userDomainBuilder.create(userRepository.readAll(con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_USERS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_USERS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return userDomains;
    }
}
