package com.codegym.casestudybookmanagerment.repository;

import com.codegym.casestudybookmanagerment.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

}