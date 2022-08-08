package com.amdevelopment.NetCracker_Project.security.services;

import com.amdevelopment.NetCracker_Project.config.exceptions.BadRequestException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DataBaseException;
import com.amdevelopment.NetCracker_Project.config.exceptions.NotFoundException;
import com.amdevelopment.NetCracker_Project.security.models.Role;
import com.amdevelopment.NetCracker_Project.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    public Role getRoleById(Integer id) {
        if(id <= 0) {
            throw new BadRequestException("Role id cannot be 0 or negative.");
        }
        return roleRepository.getRoleById(id);
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public void insertNewRole(String name) {
        if(name.equals("") || name == null) {
            throw new BadRequestException("Role name cannot be empty or null.");
        }
        try {
            roleRepository.insertNewRole(name);
        }catch (RuntimeException e) {
            throw new DataBaseException("Error while writing the role information.");
        }

    }

    public void updateRole(Integer id, String name) {
        if(id <= 0) {
            throw new BadRequestException("Role id cannot be 0 or negative.");
        }
        if(name.equals("") || name == null) {
            throw new BadRequestException("Role name cannot be empty or be null.");
        }
        Role role = roleRepository.getRoleById(id);
        if(role == null) {
            throw new NotFoundException("The role id must be the id of an existing role.");
        }
        try{
            roleRepository.updateRole(id, name);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while updating the role information.");
        }

    }

}
