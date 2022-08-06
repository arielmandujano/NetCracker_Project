package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.User;
import com.amdevelopment.NetCracker_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAdminsFiltered")
    public Iterable<User> getAdminsByData(@RequestParam(required = false) String name, @RequestParam(required = false) String lastName, @RequestParam(required = false) Integer id, @RequestParam(required = false) String email) {
        return userService.getAdminsByData(name, lastName, id, email);
    }
}
