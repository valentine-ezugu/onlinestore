package com.valentine.bookstore.repository;

import com.valentine.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByname(String name);
}
