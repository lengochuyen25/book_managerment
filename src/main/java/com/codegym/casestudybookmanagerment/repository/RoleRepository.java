package com.codegym.casestudybookmanagerment.repository;

import com.codegym.casestudybookmanagerment.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}