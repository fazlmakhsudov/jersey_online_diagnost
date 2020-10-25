package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.SymptomRepository;
import com.practice.online_diagnost.repositories.entities.builders.SymptomEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.SymptomDomain;
import com.practice.online_diagnost.services.domains.builders.SymptomDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class SymptomServiceImpl implements SymptomService {
    private static final Logger LOGGER = Logger.getLogger("SymptomServiceImpl");
    private final SymptomRepository symptomRepository;
    private final SymptomEntityBuilder symptomEntityBuilder;
    private final SymptomDomainBuilder symptomDomainBuilder;

    public SymptomServiceImpl(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
        this.symptomEntityBuilder = new SymptomEntityBuilder();
        this.symptomDomainBuilder = new SymptomDomainBuilder();

    }

    @Override
    public int add(SymptomDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = symptomRepository.create(symptomEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_SYMPTOM);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_SYMPTOM, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public SymptomDomain find(int id) throws ServiceException {
        Connection con = null;
        SymptomDomain symptomDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            symptomDomain = symptomDomainBuilder.create(symptomRepository.read(id, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return symptomDomain;
    }

    @Override
    public SymptomDomain find(String name) throws ServiceException {
        Connection con = null;
        SymptomDomain symptomDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            symptomDomain = symptomDomainBuilder.create(symptomRepository.read(name, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_SYMPTOM_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return symptomDomain;
    }

    @Override
    public List<SymptomDomain> findForDiagnoses(int diagnosesId) throws ServiceException {
        Connection con = null;
        List<SymptomDomain> symptomDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            symptomDomains = symptomDomainBuilder.create(symptomRepository.readForDiagnoses(diagnosesId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_SYMPTOMS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_SYMPTOMS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return symptomDomains;
    }

    @Override
    public List<SymptomDomain> findForDiseases(int diseasesId) throws ServiceException {
        Connection con = null;
        List<SymptomDomain> symptomDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            symptomDomains = symptomDomainBuilder.create(symptomRepository.readForDiseases(diseasesId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_SYMPTOMS_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_SYMPTOMS_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return symptomDomains;
    }


    @Override
    public boolean save(SymptomDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = symptomRepository.update(symptomEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_SYMPTOM);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_SYMPTOM, e);
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
            flag = symptomRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_SYMPTOM);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_SYMPTOM, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<SymptomDomain> findAll() throws ServiceException {
        Connection con = null;
        List<SymptomDomain> symptomDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            symptomDomains = symptomDomainBuilder.create(symptomRepository.readAll(con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_SYMPTOMS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_SYMPTOMS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return symptomDomains;
    }

}
