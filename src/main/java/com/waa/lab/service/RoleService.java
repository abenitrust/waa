package com.waa.lab.service;

import com.waa.lab.domain.Role;
import com.waa.lab.domain.RoleEnum;
import com.waa.lab.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findByName(String roleString);
}
