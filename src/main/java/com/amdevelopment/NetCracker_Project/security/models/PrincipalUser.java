package com.amdevelopment.NetCracker_Project.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PrincipalUser implements UserDetails {
    private String firstName;
    private String lastName;
    private String userName;
    private String address;
    private String email;
    private String phoneNumber;
    private String picture;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public PrincipalUser(String firstName, String lastName, String userName, String address, String email, String phoneNumber, String picture, String password, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalUser build(User user) {
        List<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoleId().getName()));
        return new PrincipalUser(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getAddress(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPicture(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPicture() {
        return picture;
    }
}
