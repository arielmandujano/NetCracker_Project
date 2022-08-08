package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.security.models.User;
import com.amdevelopment.NetCracker_Project.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAdminsFiltered")
    @PreAuthorize("hasAuthority('Admin')")
    public Iterable<User> getAdminsByData(@RequestParam(required = false) String name, @RequestParam(required = false) String lastName, @RequestParam(required = false) Integer id, @RequestParam(required = false) String email) {
        return userService.getAdminsByData(name, lastName, id, email);
    }
}
