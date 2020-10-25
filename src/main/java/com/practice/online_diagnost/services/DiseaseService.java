package com.practice.online_diagnost.services;


import com.practice.online_diagnost.exceptions.ServiceException;
import com.practice.online_diagnost.services.domains.DiseaseDomain;

public interface DiseaseService extends BaseService<DiseaseDomain> {
    DiseaseDomain find(String name) throws ServiceException;
}
