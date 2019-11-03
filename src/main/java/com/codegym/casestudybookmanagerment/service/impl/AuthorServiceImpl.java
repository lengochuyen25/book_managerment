package com.codegym.casestudybookmanagerment.service.impl;

import com.codegym.casestudybookmanagerment.model.Author;
import com.codegym.casestudybookmanagerment.repository.AuthorRepository;
import com.codegym.casestudybookmanagerment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void save(Author author  ) {
        authorRepository.save(author);
    }

    @Override
    public void remove(Long id) {
        authorRepository.deleteById(id);
    }
}
