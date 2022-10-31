package com.managersystem.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> save(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    Optional<T> findById(int id);

    List<T> findAll();
}
