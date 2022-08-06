package com.amdevelopment.NetCracker_Project.services;

import com.amdevelopment.NetCracker_Project.config.exceptions.BadRequestException;
import com.amdevelopment.NetCracker_Project.config.exceptions.DataBaseException;
import com.amdevelopment.NetCracker_Project.config.exceptions.NotFoundException;
import com.amdevelopment.NetCracker_Project.models.Car;
import com.amdevelopment.NetCracker_Project.models.Role;
import com.amdevelopment.NetCracker_Project.models.User;
import com.amdevelopment.NetCracker_Project.repositories.RoleRepository;
import com.amdevelopment.NetCracker_Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public Iterable<User> getUsersByData(String name, String lastName, Integer id, String email) {
        return userRepository.getUsersByData(name, lastName, id, email);
    }

    public Iterable<User> getAdminsByData(String name, String lastName, Integer id, String email) {
        return userRepository.getAdminsByData(name, lastName, id, email);
    }

    public User getUserById(Integer id) {
        if(id <= 0) {
            throw new BadRequestException("User id cannot be 0 or negative.");
        }
        User user = userRepository.getUserById(id);
        if(user == null) {
            throw new NotFoundException("There is no car with id = " + id);
        }
        return user;
    }

    public void insertNewUser(String name, String lastName, Integer roleId, String address, String email, String number, String picture) {
        if(name.equals("") || name == null) {
            throw new BadRequestException("User name cannot be empty or be null.");
        }
        if(lastName.equals("") || lastName == null) {
            throw new BadRequestException("User last name cannot be empty or be null.");
        }
        if(email.equals("") || email == null) {
            throw new BadRequestException("User email cannot be empty or be null.");
        }
        if(roleId <= 0) {
            throw new BadRequestException("Role id cannot be 0 or be negative.");
        }
        Role role = roleRepository.getRoleById(roleId);
        if(role == null) {
            throw new NotFoundException("Role id must be the id of an existing role.");
        }
        try {
            userRepository.insertNewUser(name, lastName, roleId, address, email, number, picture);
        } catch (RuntimeException e) {
            throw new DataBaseException("Error while writing the user information.");
        }
    }

}
