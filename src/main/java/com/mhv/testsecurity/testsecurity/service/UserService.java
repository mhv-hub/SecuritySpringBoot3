package com.mhv.testsecurity.testsecurity.service;

import com.mhv.testsecurity.testsecurity.dao.UserRepository;
import com.mhv.testsecurity.testsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserByUserName(String userName){
        return userRepository.findUserByUserUserName(userName);
    }

    public User saveNewUser(User user){
        user.setUserAccountNonExpired(true);
        user.setUserAccountNonLocked(true);
        user.setUserPasswordNonExpired(true);
        user.setUserEnabled(true);
        return userRepository.save(user);
    }
}
