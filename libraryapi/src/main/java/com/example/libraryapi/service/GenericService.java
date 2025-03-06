package com.example.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService<T> {
    List<T> findAll();
    Optional<T> findById(UUID id);
    T save(T entity);
    void delete(UUID id);
}


