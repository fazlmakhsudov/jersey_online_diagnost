package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.DiagnosRepository;
import com.practice.online_diagnost.repositories.entities.builders.DiagnosEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.DiagnosDomain;
import com.practice.online_diagnost.services.domains.DiseaseDomain;
import com.practice.online_diagnost.services.domains.builders.DiagnosDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class DiagnosServiceImpl implements DiagnosService {
    private static final Logger LOGGER = Logger.getLogger("DiagnosServiceImpl");
    private final DiagnosRepository diagnosRepository;
    private final DiagnosEntityBuilder diagnosEntityBuilder;
    private final DiagnosDomainBuilder diagnosDomainBuilder;

    public DiagnosServiceImpl(DiagnosRepository diagnosRepository) {
        this.diagnosRepository = diagnosRepository;
        this.diagnosEntityBuilder = new DiagnosEntityBuilder();
        this.diagnosDomainBuilder = new DiagnosDomainBuilder();

    }

    @Override
    public int add(DiagnosDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = diagnosRepository.create(diagnosEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public DiagnosDomain find(int id) throws ServiceException {
        AssignmentService assignmentService = (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        DiagnosDomain diagnosDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diagnosDomain = diagnosDomainBuilder.create(diagnosRepository.read(id, con));
            diagnosDomain.setAssignments(assignmentService.findForDiagnoses(diagnosDomain.getId()));
            diagnosDomain.setSymptoms(symptomService.findForDiagnoses(diagnosDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diagnosDomain;
    }

    @Override
    public DiagnosDomain find(String name) throws ServiceException {
        AssignmentService assignmentService = (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        DiagnosDomain diagnosDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diagnosDomain = diagnosDomainBuilder.create(diagnosRepository.read(name, con));
            diagnosDomain.setAssignments(assignmentService.findForDiagnoses(diagnosDomain.getId()));
            diagnosDomain.setSymptoms(symptomService.findForDiagnoses(diagnosDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DIAGNOS_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diagnosDomain;
    }

    @Override
    public List<DiagnosDomain> findForTreatmentHistories(int treatmentHistoriesId) throws ServiceException {
        AssignmentService assignmentService = (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        List<DiagnosDomain> diagnosDomainList = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diagnosDomainList = diagnosDomainBuilder.create(diagnosRepository.readForTreatmentHistories(treatmentHistoriesId, con));
            for (DiagnosDomain diagnosDomain : diagnosDomainList) {
                diagnosDomain.setAssignments(assignmentService.findForDiagnoses(diagnosDomain.getId()));
                diagnosDomain.setSymptoms(symptomService.findForDiagnoses(diagnosDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_DIAGNOSES_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_DIAGNOSES_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diagnosDomainList;
    }

    @Override
    public boolean save(DiagnosDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = diagnosRepository.update(diagnosEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_DIAGNOS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_DIAGNOS, e);
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
            flag = diagnosRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_DIAGNOS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_DIAGNOS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<DiagnosDomain> findAll() throws ServiceException {
        AssignmentService assignmentService = (AssignmentService) ServiceFactory.createService(ServiceType.ASSIGNMENT_SERVICE);
        SymptomService symptomService = (SymptomService) ServiceFactory.createService(ServiceType.SYMPTOM_SERVICE);
        Connection con = null;
        List<DiagnosDomain> diagnosDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diagnosDomains = diagnosDomainBuilder.create(diagnosRepository.readAll(con));
            for (DiagnosDomain diagnosDomain : diagnosDomains) {
                diagnosDomain.setAssignments(assignmentService.findForDiagnoses(diagnosDomain.getId()));
                diagnosDomain.setSymptoms(symptomService.findForDiagnoses(diagnosDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DIAGNOSS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DIAGNOSS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diagnosDomains;
    }
}
