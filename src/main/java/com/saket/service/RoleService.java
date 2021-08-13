package com.saket.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.saket.model.Role;
import com.saket.repository.RoleDaoImpl;

import java.util.logging.Logger;

public class RoleService {

    private Logger logger;
    private RoleDaoImpl roleDao;

    @Autowired
    public RoleService(Logger logger, RoleDaoImpl roleDao){
        this.logger = logger;
        this.roleDao = roleDao;
    }

    public Role getRoleByName(String name){
        return roleDao.getRoleByName(name);
    }
}
