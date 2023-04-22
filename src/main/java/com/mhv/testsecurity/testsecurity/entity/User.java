package com.mhv.testsecurity.testsecurity.entity;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class User {

    int userId;
    String userName;
    String userRole;
    boolean userPasswordNonExpired;
    boolean userAccountNonLocked;
    boolean userAccountNonExpired;
    boolean userEnabled;

    public User(int userId, String userName, String userRole, boolean userPasswordNonExpired, boolean userAccountNonLocked, boolean userAccountNonExpired, boolean userEnabled) {
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
        this.userPasswordNonExpired = userPasswordNonExpired;
        this.userAccountNonLocked = userAccountNonLocked;
        this.userAccountNonExpired = userAccountNonExpired;
        this.userEnabled = userEnabled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isUserPasswordNonExpired() {
        return userPasswordNonExpired;
    }

    public void setUserPasswordNonExpired(boolean userPasswordNonExpired) {
        this.userPasswordNonExpired = userPasswordNonExpired;
    }

    public boolean isUserAccountNonLocked() {
        return userAccountNonLocked;
    }

    public void setUserAccountNonLocked(boolean userAccountNonLocked) {
        this.userAccountNonLocked = userAccountNonLocked;
    }

    public boolean isUserAccountNonExpired() {
        return userAccountNonExpired;
    }

    public void setUserAccountNonExpired(boolean userAccountNonExpired) {
        this.userAccountNonExpired = userAccountNonExpired;
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPasswordNonExpired=" + userPasswordNonExpired +
                ", userAccountNonLocked=" + userAccountNonLocked +
                ", userAccountNonExpired=" + userAccountNonExpired +
                ", userEnabled=" + userEnabled +
                '}';
    }
}
