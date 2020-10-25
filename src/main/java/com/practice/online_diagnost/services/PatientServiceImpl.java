package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.PatientRepository;
import com.practice.online_diagnost.repositories.entities.builders.PatientEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.PatientDomain;
import com.practice.online_diagnost.services.domains.builders.PatientDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class PatientServiceImpl implements PatientService {
    private static final Logger LOGGER = Logger.getLogger("PatientServiceImpl");
    private final PatientRepository patientRepository;
    private final PatientEntityBuilder patientEntityBuilder;
    private final PatientDomainBuilder patientDomainBuilder;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.patientEntityBuilder = new PatientEntityBuilder();
        this.patientDomainBuilder = new PatientDomainBuilder();

    }

    @Override
    public int add(PatientDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = patientRepository.create(patientEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_PATIENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_PATIENT, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public PatientDomain find(int id) throws ServiceException {
        TreatmentHistoryService treatmentHistoryService =
                (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        PatientDomain patientDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            patientDomain = patientDomainBuilder.create(patientRepository.read(id, con));
            patientDomain.setTreatmentHistory(treatmentHistoryService.findForPatients(patientDomain.getId()));
            patientDomain.setUserDomain(userService.findForPatients(patientDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENT_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENT_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return patientDomain;
    }


    @Override
    public boolean save(PatientDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = patientRepository.update(patientEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_PATIENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_PATIENT, e);
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
            flag = patientRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_PATIENT);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_PATIENT, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<PatientDomain> findAll() throws ServiceException {
        TreatmentHistoryService treatmentHistoryService =
                (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        List<PatientDomain> patientDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            patientDomains = patientDomainBuilder.create(patientRepository.readAll(con));
            for (PatientDomain patientDomain : patientDomains) {
                patientDomain.setTreatmentHistory(treatmentHistoryService.findForPatients(patientDomain.getId()));
                patientDomain.setUserDomain(userService.findForPatients(patientDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENTS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENTS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return patientDomains;
    }

    @Override
    public List<PatientDomain> findForDiseases(int diseasesId) throws ServiceException {
        TreatmentHistoryService treatmentHistoryService =
                (TreatmentHistoryService) ServiceFactory.createService(ServiceType.TREATMENT_HISTORY_SERVICE);
        UserService userService = (UserService) ServiceFactory.createService(ServiceType.USER_SERVICE);
        Connection con = null;
        List<PatientDomain> patientDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            patientDomains = patientDomainBuilder.create(patientRepository.readForDiseases(diseasesId, con));
            for (PatientDomain patientDomain : patientDomains) {
                patientDomain.setTreatmentHistory(treatmentHistoryService.findForPatients(patientDomain.getId()));
                patientDomain.setUserDomain(userService.findForPatients(patientDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENTS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENTS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return patientDomains;
    }
}
