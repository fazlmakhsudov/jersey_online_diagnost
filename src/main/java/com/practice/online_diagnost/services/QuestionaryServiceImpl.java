package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.QuestionaryRepository;
import com.practice.online_diagnost.repositories.entities.builders.QuestionaryEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.QuestionaryDomain;
import com.practice.online_diagnost.services.domains.builders.QuestionaryDomainBuilder;
import com.practice.online_diagnost.services.factory.ServiceFactory;
import com.practice.online_diagnost.services.factory.ServiceType;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class QuestionaryServiceImpl implements QuestionaryService {
    private static final Logger LOGGER = Logger.getLogger("QuestionaryServiceImpl");
    private final QuestionaryRepository questionaryRepository;
    private final QuestionaryEntityBuilder questionaryEntityBuilder;
    private final QuestionaryDomainBuilder questionaryDomainBuilder;

    public QuestionaryServiceImpl(QuestionaryRepository questionaryRepository) {
        this.questionaryRepository = questionaryRepository;
        this.questionaryEntityBuilder = new QuestionaryEntityBuilder();
        this.questionaryDomainBuilder = new QuestionaryDomainBuilder();

    }

    @Override
    public int add(QuestionaryDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = questionaryRepository.create(questionaryEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTIONARY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTIONARY, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public QuestionaryDomain find(int id) throws ServiceException {
        QuestionService questionService = (QuestionService) ServiceFactory.createService(ServiceType.QUESTION_SERVICE);
        Connection con = null;
        QuestionaryDomain questionaryDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionaryDomain = questionaryDomainBuilder.create(questionaryRepository.read(id, con));
            questionaryDomain.setQuestions(questionService.findForQuestionaries(questionaryDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionaryDomain;
    }

    @Override
    public QuestionaryDomain find(String name) throws ServiceException {
        QuestionService questionService = (QuestionService) ServiceFactory.createService(ServiceType.QUESTION_SERVICE);
        Connection con = null;
        QuestionaryDomain questionaryDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionaryDomain = questionaryDomainBuilder.create(questionaryRepository.read(name, con));
            questionaryDomain.setQuestions(questionService.findForQuestionaries(questionaryDomain.getId()));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTIONARY_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionaryDomain;
    }

    @Override
    public boolean save(QuestionaryDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = questionaryRepository.update(questionaryEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTIONARY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTIONARY, e);
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
            flag = questionaryRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTIONARY);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTIONARY, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<QuestionaryDomain> findAll() throws ServiceException {
        QuestionService questionService = (QuestionService) ServiceFactory.createService(ServiceType.QUESTION_SERVICE);
        Connection con = null;
        List<QuestionaryDomain> questionaryDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionaryDomains = questionaryDomainBuilder.create(questionaryRepository.readAll(con));
            for (QuestionaryDomain questionaryDomain : questionaryDomains) {
                questionaryDomain.setQuestions(questionService.findForQuestionaries(questionaryDomain.getId()));
            }
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONARIES);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONARIES, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionaryDomains;
    }
}
