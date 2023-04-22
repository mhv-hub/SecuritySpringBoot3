package com.mhv.testsecurity.testsecurity.dao;

import com.mhv.testsecurity.testsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByUserUserName(String userName);
}
