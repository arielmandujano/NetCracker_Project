package com.amdevelopment.NetCracker_Project.security.services;

import com.amdevelopment.NetCracker_Project.security.models.PrincipalUser;
import com.amdevelopment.NetCracker_Project.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserame(username).get();
        return PrincipalUser.build(user);
    }
}
