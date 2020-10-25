package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.QuestionDomain;

import java.util.List;

public interface QuestionService extends BaseService<QuestionDomain> {
    QuestionDomain find(String name) throws ServiceException;

    List<QuestionDomain> findForQuestionaries(int questionariesId) throws ServiceException;

}
