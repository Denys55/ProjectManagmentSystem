package com.managersystem.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> save(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    Optional<T> findById(int id);

    List<T> findAll();
}
