package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.Messages;
import com.practice.online_diagnost.exceptions.RepositoryException;
import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.repositories.QuestionRepository;
import com.practice.online_diagnost.repositories.entities.builders.QuestionEntityBuilder;
import com.practice.online_diagnost.repositories.util.DBManager;
import com.practice.online_diagnost.services.domains.QuestionDomain;
import com.practice.online_diagnost.services.domains.builders.QuestionDomainBuilder;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


public class QuestionServiceImpl implements QuestionService {
    private static final Logger LOGGER = Logger.getLogger("QuestionServiceImpl");
    private final QuestionRepository questionRepository;
    private final QuestionEntityBuilder questionEntityBuilder;
    private final QuestionDomainBuilder questionDomainBuilder;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.questionEntityBuilder = new QuestionEntityBuilder();
        this.questionDomainBuilder = new QuestionDomainBuilder();

    }

    @Override
    public int add(QuestionDomain item) throws ServiceException {
        Connection con = null;
        int row = -1;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            row = questionRepository.create(questionEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_QUESTION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return row;
    }

    @Override
    public QuestionDomain find(int id) throws ServiceException {
        Connection con = null;
        QuestionDomain questionDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionDomain = questionDomainBuilder.create(questionRepository.read(id, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_ID);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_ID, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionDomain;
    }

    @Override
    public QuestionDomain find(String name) throws ServiceException {
        Connection con = null;
        QuestionDomain questionDomain = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionDomain = questionDomainBuilder.create(questionRepository.read(name, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_NAME);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_QUESTION_BY_NAME, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionDomain;
    }

    @Override
    public List<QuestionDomain> findForQuestionaries(int questionariesId) throws ServiceException {
        Connection con = null;
        List<QuestionDomain> questionDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionDomains = questionDomainBuilder.create(questionRepository.readForQuestionaries(questionariesId, con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionDomains;
    }

    @Override
    public boolean save(QuestionDomain item) throws ServiceException {
        Connection con = null;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            flag = questionRepository.update(questionEntityBuilder.create(item), con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_UPDATE_QUESTION, e);
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
            flag = questionRepository.delete(id, con);
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTION);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_DELETE_QUESTION, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return flag;
    }

    @Override
    public List<QuestionDomain> findAll() throws ServiceException {
        Connection con = null;
        List<QuestionDomain> questionDomains = null;
        try {
            con = DBManager.getInstance().getConnectionFromPool();
            questionDomains = questionDomainBuilder.create(questionRepository.readAll(con));
            con.commit();
        } catch (RepositoryException e) {
            DBManager.rollback(con);
            LOGGER.severe(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONS);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_QUESTIONS, e);
        } catch (Exception e) {
            LOGGER.severe(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
        } finally {
            DBManager.releaseConnection(con);
        }
        return questionDomains;
    }
}
