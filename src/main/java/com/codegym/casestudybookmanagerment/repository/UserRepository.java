package com.codegym.casestudybookmanagerment.repository;

import com.codegym.casestudybookmanagerment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}

