package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.MedicDomain;

public interface MedicService extends BaseService<MedicDomain> {
    MedicDomain find(String specitalization) throws ServiceException;
}
