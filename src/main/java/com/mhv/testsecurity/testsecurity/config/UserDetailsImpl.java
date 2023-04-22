package com.mhv.testsecurity.testsecurity.config;

import com.mhv.testsecurity.testsecurity.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getUserRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isUserAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isUserAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isUserPasswordNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isUserEnabled();
    }
}
