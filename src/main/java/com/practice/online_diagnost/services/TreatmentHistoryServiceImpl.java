package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.TreatmentHistoryRepository;
import com.practice.online_diagnost.repositories.entities.builders.TreatmentHistoryEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;
import com.practice.online_diagnost.services.domains.builders.TreatmentHistoryDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class TreatmentHistoryServiceImpl implements TreatmentHistoryService {
    private static final Logger LOGGER = Logger.getLogger("TreatmentHistoryServiceImpl");
    private final TreatmentHistoryRepository treatmentHistoryRepository;
    private final TreatmentHistoryEntityBuilder treatmentHistoryEntityBuilder;
    private final TreatmentHistoryDomainBuilder treatmentHistoryDomainBuilder;

    public TreatmentHistoryServiceImpl(TreatmentHistoryRepository treatmentHistoryRepository) {
        this.treatmentHistoryRepository = treatmentHistoryRepository;
        this.treatmentHistoryEntityBuilder = new TreatmentHistoryEntityBuilder();
        this.treatmentHistoryDomainBuilder = new TreatmentHistoryDomainBuilder();

    }

    @Override
    public int add(TreatmentHistoryDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = treatmentHistoryRepository.create(treatmentHistoryEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_TREATMENTHISTORY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_TREATMENTHISTORY, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public TreatmentHistoryDomain find(int id) throws ServiceException {
        DiagnosService diagnosService = (DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE);
        Connection con = null;
        TreatmentHistoryDomain treatmentHistoryDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            treatmentHistoryDomain = treatmentHistoryDomainBuilder.create(treatmentHistoryRepository.read(id, con));
            treatmentHistoryDomain.setDiagnoses(diagnosService.findForTreatmentHistories(treatmentHistoryDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_TREATMENTHISTORY_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return treatmentHistoryDomain;
    }


    @Override
    public boolean save(TreatmentHistoryDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = treatmentHistoryRepository.update(treatmentHistoryEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_TREATMENTHISTORY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_TREATMENTHISTORY, e);
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
            flag = treatmentHistoryRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_TREATMENTHISTORY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_TREATMENTHISTORY, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<TreatmentHistoryDomain> findAll() throws ServiceException {
        DiagnosService diagnosService = (DiagnosService) ServiceFactory.createService(ServiceType.DIAGNOS_SERVICE);
        Connection con = null;
        List<TreatmentHistoryDomain> treatmentHistoryDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            treatmentHistoryDomains = treatmentHistoryDomainBuilder.create(treatmentHistoryRepository.readAll(con));
            for (TreatmentHistoryDomain treatmentHistoryDomain : treatmentHistoryDomains) {
                treatmentHistoryDomain.setDiagnoses(diagnosService.findForTreatmentHistories(treatmentHistoryDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_TREATMENTHISTORIES);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_TREATMENTHISTORIES, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return treatmentHistoryDomains;
    }

    @Override
    public TreatmentHistoryDomain findForPatients(int patientsId) throws ServiceException {
        return null;
    }
}
