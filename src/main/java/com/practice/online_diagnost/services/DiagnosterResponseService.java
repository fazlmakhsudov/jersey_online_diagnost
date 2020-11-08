package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.MySqlDiagnosterResponseRepository;
import com.practice.online_diagnost.repositories.entities.builders.DiagnosterResponseEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.DiagnosterResponseDomain;
import com.practice.online_diagnost.services.domains.builders.DiagnosterResponseDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class DiagnosterResponseService {
    private static final Logger LOGGER = Logger.getLogger(DiagnosterResponseService.class.getSimpleName());
    private final MySqlDiagnosterResponseRepository mySqlDiagnosterResponseRepository;
    private final DiagnosterResponseEntityBuilder diagnosterResponseEntityBuilder;
    private final DiagnosterResponseDomainBuilder diagnosterResponseDomainBuilder;

    public DiagnosterResponseService() {
        this.mySqlDiagnosterResponseRepository = new MySqlDiagnosterResponseRepository();
        this.diagnosterResponseEntityBuilder = new DiagnosterResponseEntityBuilder();
        this.diagnosterResponseDomainBuilder = new DiagnosterResponseDomainBuilder();

    }


    public int add(DiagnosterResponseDomain item, String tableName) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = mySqlDiagnosterResponseRepository.create(diagnosterResponseEntityBuilder.create(item), con, tableName);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOSTER_RESPONSE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOSTER_RESPONSE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    public int addBatch(List<DiagnosterResponseDomain> diagnosterResponseDomains, String tableName) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            row = 0;
            con = DBManager.getInstance().getConnectionFromPool();
            for (DiagnosterResponseDomain diagnosterResponseDomain : diagnosterResponseDomains) {
                row += mySqlDiagnosterResponseRepository.create(diagnosterResponseEntityBuilder.create(diagnosterResponseDomain), con, tableName);
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            row = -1;
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOSTER_RESPONSE);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_DIAGNOSTER_RESPONSE, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    public List<DiagnosterResponseDomain> findAll(String tableName) throws ServiceException {
        Connection con = null;
        List<DiagnosterResponseDomain> diagnosterResponseDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            diagnosterResponseDomains = diagnosterResponseDomainBuilder.create(mySqlDiagnosterResponseRepository.readAll(con, tableName));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_DIAGNOSTER_RESPONSES, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return diagnosterResponseDomains;
    }

    public List<DiagnosterResponseDomain> findAllForPatient(String tableName, int patientsId) throws ServiceException {
        Connection con = null;
        List<DiagnosterResponseDomain> diagnosterResponseDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();

            diagnosterResponseDomains =
                    diagnosterResponseDomainBuilder.create(mySqlDiagnosterResponseRepository.readAllForPatient(con, tableName, patientsId));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_DIAGNOSTER_RESPONSES_WITH_LIMITATION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_DIAGNOSTER_RESPONSES_WITH_LIMITATION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }

        return diagnosterResponseDomains;
    }

}
