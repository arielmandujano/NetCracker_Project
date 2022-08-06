package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.Role;
import com.amdevelopment.NetCracker_Project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/getAllRoles")
    public Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/getRoleById")
    public Role getRoleById(@RequestParam Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/saveNewRole")
    public void insertNewRole(@RequestParam String name) {
        roleService.insertNewRole(name);
    }

    @PutMapping("/updateRole")
    public void updateRole(@RequestParam Integer id, @RequestParam String name) {
        roleService.updateRole(id, name);
    }
}
