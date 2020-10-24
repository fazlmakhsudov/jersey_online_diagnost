package com.practice.online_diagnost.repositories;


import com.practice.online_diagnost.exceptions.RepositoryException;

import java.sql.Connection;
import java.util.List;

public interface BaseRepository<T> {

    int create(T item, Connection con) throws RepositoryException;

    T read(int id, Connection con) throws RepositoryException;

    boolean update(T item, Connection con) throws RepositoryException;

    boolean delete(int id, Connection con) throws RepositoryException;

    List<T> readAll(Connection con) throws RepositoryException;
}
