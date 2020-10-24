package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.RoleRepository;
import com.practice.online_diagnost.repositories.entities.builders.RoleEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.RoleDomain;
import com.practice.online_diagnost.services.domains.builders.RoleDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = Logger.getLogger("RoleServiceImpl");
    private final RoleRepository roleRepository;
    private final RoleEntityBuilder roleEntityBuilder;
    private final RoleDomainBuilder roleDomainBuilder;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.roleEntityBuilder = new RoleEntityBuilder();
        this.roleDomainBuilder = new RoleDomainBuilder();

    }

    @Override
    public int add(RoleDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = roleRepository.create(roleEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_ROLE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_ROLE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public RoleDomain find(int id) throws ServiceException {
        Connection con = null;
        RoleDomain roleDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            roleDomain = roleDomainBuilder.create(roleRepository.read(id, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return roleDomain;
    }

    @Override
    public RoleDomain find(String name) throws ServiceException {
        Connection con = null;
        RoleDomain roleDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            roleDomain = roleDomainBuilder.create(roleRepository.read(name, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ROLE_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return roleDomain;
    }

    @Override
    public boolean save(RoleDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = roleRepository.update(roleEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_ROLE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_ROLE, e);
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
            flag = roleRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_ROLE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_ROLE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<RoleDomain> findAll() throws ServiceException {
        Connection con = null;
        List<RoleDomain> roleDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            roleDomains = roleDomainBuilder.create(roleRepository.readAll(con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_ROLES);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_ROLES, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return roleDomains;
    }
}
