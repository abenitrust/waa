package com.waa.lab.controller;

import com.waa.lab.domain.Role;
import com.waa.lab.domain.RoleEnum;
import com.waa.lab.exception.InvalidRoleException;
import com.waa.lab.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private RoleService roleService ;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    List<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{roleString}")
    Role findByName(@PathVariable String roleString) {
        return roleService.findByName(roleString).orElse(null);
    }
}
