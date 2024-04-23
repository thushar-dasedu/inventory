package com.InventoryManagement.service;

import com.InventoryManagement.entity.Role;
import com.InventoryManagement.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleService  {

    @Autowired
    private RoleRepository roleDao;


    public Role findByName(String name) {
        // Find role by name using the roleDao
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
