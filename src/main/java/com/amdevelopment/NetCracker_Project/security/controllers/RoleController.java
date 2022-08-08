package com.amdevelopment.NetCracker_Project.security.controllers;

import com.amdevelopment.NetCracker_Project.security.models.Role;
import com.amdevelopment.NetCracker_Project.security.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getAllRoles")
    @PreAuthorize("hasAuthority('Admin')")
    public Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/getRoleById")
    @PreAuthorize("hasAuthority('Admin')")
    public Role getRoleById(@RequestParam Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/saveNewRole")
    @PreAuthorize("hasAuthority('Admin')")
    public void insertNewRole(@RequestParam String name) {
        roleService.insertNewRole(name);
    }

    @PutMapping("/updateRole")
    @PreAuthorize("hasAuthority('Admin')")
    public void updateRole(@RequestParam Integer id, @RequestParam String name) {
        roleService.updateRole(id, name);
    }
}
