package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.DiseaseRepository;
import com.practice.online_diagnost.repositories.entities.builders.DiseaseEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.domains.builders.DiseaseDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class DiseaseServiceImpl implements DiseaseService {
    private static final Logger LOGGER = Logger.getLogger("DiseaseServiceImpl");
    private final DiseaseRepository diseaseRepository;
    private final DiseaseEntityBuilder diseaseEntityBuilder;
    private final DiseaseDomainBuilder diseaseDomainBuilder;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseEntityBuilder = new DiseaseEntityBuilder();
        this.diseaseDomainBuilder = new DiseaseDomainBuilder();

    }

    @Override
    public int add(DiseaseDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = diseaseRepository.create(diseaseEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DISEASE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DISEASE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public DiseaseDomain find(int id) throws ServiceException {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        DiseaseDomain diseaseDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diseaseDomain = diseaseDomainBuilder.create(diseaseRepository.read(id, con));
            diseaseDomain.setPatients(patientService.findForDiseases(diseaseDomain.getId()));
            diseaseDomain.setSymptoms(symptomService.findForDiseases(diseaseDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diseaseDomain;
    }

    @Override
    public DiseaseDomain find(String name) throws ServiceException {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        DiseaseDomain diseaseDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diseaseDomain = diseaseDomainBuilder.create(diseaseRepository.read(name, con));
            diseaseDomain.setPatients(patientService.findForDiseases(diseaseDomain.getId()));
            diseaseDomain.setSymptoms(symptomService.findForDiseases(diseaseDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DISEASE_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diseaseDomain;
    }

    @Override
    public boolean save(DiseaseDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = diseaseRepository.update(diseaseEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_DISEASE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_DISEASE, e);
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
            flag = diseaseRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_DISEASE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_DISEASE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<DiseaseDomain> findAll() throws ServiceException {
        PatientService patientService = (PatientService) ServiceFactory.createService(ServiceType.PATIENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        List<DiseaseDomain> diseaseDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diseaseDomains = diseaseDomainBuilder.create(diseaseRepository.readAll(con));
            for (DiseaseDomain diseaseDomain : diseaseDomains) {
                diseaseDomain.setPatients(patientService.findForDiseases(diseaseDomain.getId()));
                diseaseDomain.setSymptoms(symptomService.findForDiseases(diseaseDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DISEASES);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DISEASES, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diseaseDomains;
    }
}
