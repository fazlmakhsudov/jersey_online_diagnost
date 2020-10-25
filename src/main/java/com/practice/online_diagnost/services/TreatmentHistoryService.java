package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.TreatmentHistoryDomain;

public interface TreatmentHistoryService extends BaseService<TreatmentHistoryDomain> {

    TreatmentHistoryDomain findForPatients(int patientsId) throws ServiceException;

}
