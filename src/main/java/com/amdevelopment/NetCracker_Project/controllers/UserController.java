package com.amdevelopment.NetCracker_Project.controllers;

import com.amdevelopment.NetCracker_Project.models.User;
import com.amdevelopment.NetCracker_Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insertNewUser")
    public void insertNewUser(@RequestParam String name,@RequestParam String lastName,@RequestParam Integer roleId,@RequestParam(required = false) String address,@RequestParam String email,@RequestParam(required = false) String number,@RequestParam(required = false) String picture) {
        userService.insertNewUser(name, lastName, roleId, address, email, number, picture);
    }

    @GetMapping("/getUsersFiltered")
    public Iterable<User> getUsersByData(@RequestParam(required = false) String name,@RequestParam(required = false) String lastName,@RequestParam(required = false) Integer id,@RequestParam(required = false) String email) {
        return userService.getUsersByData(name, lastName, id, email);
    }

}
