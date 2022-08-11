package com.amdevelopment.NetCracker_Project.security.controllers;

import com.amdevelopment.NetCracker_Project.security.dto.JwtDto;
import com.amdevelopment.NetCracker_Project.security.dto.LoginUser;
import com.amdevelopment.NetCracker_Project.security.dto.NewUser;
import com.amdevelopment.NetCracker_Project.security.jwt.JwtProvider;
import com.amdevelopment.NetCracker_Project.security.models.Role;
import com.amdevelopment.NetCracker_Project.security.models.User;
import com.amdevelopment.NetCracker_Project.security.services.RoleService;
import com.amdevelopment.NetCracker_Project.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity("Error in fields or invalid email address.",HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUsername(newUser.getUserName())) {
            return new ResponseEntity("The username is no longer available.", HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(newUser.getEmail())) {
            return new ResponseEntity("This email address is already registered.", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setRoleId(roleService.getRoleByName("User").get());
        if(newUser.getRole().equals("Admin")){
            user.setRoleId(roleService.getRoleByName("Admin").get());
        }
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setAddress(newUser.getAddress());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setPicture(newUser.getPicture());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUserName());
        System.out.println(user.getPassword());
        userService.saveUser(user);
        return new ResponseEntity("User created.",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity("Error in fields.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}
