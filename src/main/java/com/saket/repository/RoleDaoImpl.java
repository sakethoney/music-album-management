package com.saket.repository;

import org.springframework.stereotype.Repository;

import com.saket.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{

    private Connection<Role> roleConnection = new Connection<>();

    @Override
    public Role getRoleByName(String name) {
        String hql = "FROM Role as r where lower(r.name) = :name";
        return roleConnection.getObjectByName(hql, name);
    }
}
