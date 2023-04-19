package com.security.model;

import java.util.List;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DummyUser implements UserDetails {

    @Override
    public String getUsername(){
        return "bill";
    }

    @Override
    public String getPassword(){
        return "12345";
    }

    @Override
    public Collection<? extends 
        GrantedAuthority> getAuthorities(){
        return List.of( () -> "READ");
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    public boolean isEnabled(){
        return true;
    }

}
