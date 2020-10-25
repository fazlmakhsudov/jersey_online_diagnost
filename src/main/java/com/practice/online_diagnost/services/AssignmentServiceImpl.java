package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.AssignmentRepository;
import com.practice.online_diagnost.repositories.entities.builders.AssignmentEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.AssignmentDomain;
import com.practice.online_diagnost.services.domains.builders.AssignmentDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class AssignmentServiceImpl implements AssignmentService {
    private static final Logger LOGGER = Logger.getLogger("AssignmentServiceImpl");
    private final AssignmentRepository assignmentRepository;
    private final AssignmentEntityBuilder assignmentEntityBuilder;
    private final AssignmentDomainBuilder assignmentDomainBuilder;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentEntityBuilder = new AssignmentEntityBuilder();
        this.assignmentDomainBuilder = new AssignmentDomainBuilder();

    }

    @Override
    public int add(AssignmentDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = assignmentRepository.create(assignmentEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_ASSIGNMENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_ASSIGNMENT, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public AssignmentDomain find(int id) throws ServiceException {
        Connection con = null;
        AssignmentDomain assignmentDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            assignmentDomain = assignmentDomainBuilder.create(assignmentRepository.read(id, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return assignmentDomain;
    }

    @Override
    public AssignmentDomain find(String name) throws ServiceException {
        Connection con = null;
        AssignmentDomain assignmentDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            assignmentDomain = assignmentDomainBuilder.create(assignmentRepository.read(name, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_ASSIGNMENT_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return assignmentDomain;
    }

    @Override
    public List<AssignmentDomain> findForDiagnoses(int diagnosesId) throws ServiceException {
        Connection con = null;
        List<AssignmentDomain> assignmentDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            assignmentDomains = assignmentDomainBuilder.create(assignmentRepository.readForDiagnoses(diagnosesId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return assignmentDomains;
    }

    @Override
    public List<AssignmentDomain> findForMedics(int medicsId) throws ServiceException {
        Connection con = null;
        List<AssignmentDomain> assignmentDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            assignmentDomains = assignmentDomainBuilder.create(assignmentRepository.readForMedics(medicsId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ASSIGNMENTS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return assignmentDomains;
    }

    @Override
    public boolean save(AssignmentDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = assignmentRepository.update(assignmentEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_ASSIGNMENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_ASSIGNMENT, e);
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
            flag = assignmentRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_ASSIGNMENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_ASSIGNMENT, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<AssignmentDomain> findAll() throws ServiceException {
        Connection con = null;
        List<AssignmentDomain> assignmentDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            assignmentDomains = assignmentDomainBuilder.create(assignmentRepository.readAll(con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_ASSIGNMENTS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_ASSIGNMENTS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return assignmentDomains;
    }

}
