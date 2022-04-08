package com.waa.lab.service.impl;

import com.waa.lab.domain.Role;
import com.waa.lab.domain.RoleEnum;
import com.waa.lab.exception.InvalidRoleException;
import com.waa.lab.repository.RoleRepository;
import com.waa.lab.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findByName(String roleString) {
        RoleEnum roleEnum = RoleEnum.ADMIN;
        try {
            RoleEnum mappedFromStr = roleEnum.getEnum(roleString);
            return roleRepository.findByName(mappedFromStr);
        } catch (InvalidRoleException e){
            return null;
        }
    }
}
