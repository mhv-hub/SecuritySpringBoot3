package com.mhv.testsecurity.testsecurity.config;

import com.mhv.testsecurity.testsecurity.entity.User;
import com.mhv.testsecurity.testsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found !!");
        return new UserDetailsImpl(user);
    }
}
