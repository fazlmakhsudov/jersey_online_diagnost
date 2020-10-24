package com.practice.online_diagnost.services;

import com.practice.online_diagnost.exceptions.ServiceException;

import java.util.List;

public interface BaseService<T> {

    int add(T item) throws ServiceException;

    T find(int id) throws ServiceException;

    boolean save(T item) throws ServiceException;

    boolean remove(int id) throws ServiceException;

    List<T> findAll() throws ServiceException;
}
