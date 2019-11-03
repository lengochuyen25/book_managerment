package com.codegym.casestudybookmanagerment.service;

import com.codegym.casestudybookmanagerment.model.Author;

import java.util.Optional;

public interface AuthorService {
    Iterable<Author> findAll();

    Optional<Author> findById(Long id);

    void save(Author author);

    void remove(Long id);
}