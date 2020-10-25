package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.MedicRepository;
import com.practice.online_diagnost.repositories.entities.builders.MedicEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.MedicDomain;
import com.practice.online_diagnost.services.domains.builders.MedicDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class MedicServiceImpl implements MedicService {
    private static final Logger LOGGER = Logger.getLogger("MedicServiceImpl");
    private final MedicRepository medicRepository;
    private final MedicEntityBuilder medicEntityBuilder;
    private final MedicDomainBuilder medicDomainBuilder;

    public MedicServiceImpl(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
        this.medicEntityBuilder = new MedicEntityBuilder();
        this.medicDomainBuilder = new MedicDomainBuilder();

    }

    @Override
    public int add(MedicDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = medicRepository.create(medicEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_MEDIC);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_MEDIC, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public MedicDomain find(int id) throws ServiceException {
        AssignmentService assignmentService =
                (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        MedicDomain medicDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            medicDomain = medicDomainBuilder.create(medicRepository.read(id, con));
            medicDomain.setAssignments(assignmentService.findForMedics(medicDomain.getId()));
            medicDomain.setUserDomain(userService.findForMedics(medicDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return medicDomain;
    }


    @Override
    public boolean save(MedicDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = medicRepository.update(medicEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_MEDIC);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_MEDIC, e);
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
            flag = medicRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_MEDIC);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_MEDIC, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<MedicDomain> findAll() throws ServiceException {
        AssignmentService assignmentService =
                (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        List<MedicDomain> medicDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            medicDomains = medicDomainBuilder.create(medicRepository.readAll(con));
            for (MedicDomain medicDomain : medicDomains) {
                medicDomain.setAssignments(assignmentService.findForMedics(medicDomain.getId()));
                medicDomain.setUserDomain(userService.findForMedics(medicDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_MEDICS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_MEDICS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return medicDomains;
    }


    @Override
    public MedicDomain find(String specitalization) throws ServiceException {
        AssignmentService assignmentService =
                (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        MedicDomain medicDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            medicDomain = medicDomainBuilder.create(medicRepository.read(specitalization, con));
            medicDomain.setAssignments(assignmentService.findForMedics(medicDomain.getId()));
            medicDomain.setUserDomain(userService.findForMedics(medicDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDIC_BY_SPECIALIZATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return medicDomain;
    }
}
