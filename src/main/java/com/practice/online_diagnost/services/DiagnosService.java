package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.DiagnosDomain;

import java.util.List;

public interface DiagnosService extends BaseService<DiagnosDomain> {
    DiagnosDomain find(String name) throws ServiceException;

    List<DiagnosDomain> findForTreatmentHistories(int treatmentHistoriesId) throws ServiceException;

}
