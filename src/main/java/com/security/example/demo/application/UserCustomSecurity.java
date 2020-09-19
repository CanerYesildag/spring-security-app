package com.security.example.demo.application;

import com.security.example.demo.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCustomSecurity extends User implements UserDetails {

    public UserCustomSecurity() {
    }

    public UserCustomSecurity(User user) {
        this.setId(user.getId());
        this.setAuthorities(user.getAuthorities());
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
    }

    @Override
    public String getUsername() {
        return null;
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
}
