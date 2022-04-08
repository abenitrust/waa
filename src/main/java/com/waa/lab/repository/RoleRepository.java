package com.waa.lab.repository;

import com.waa.lab.domain.Role;
import com.waa.lab.domain.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();
    Optional<Role> findByName(RoleEnum name);
}
