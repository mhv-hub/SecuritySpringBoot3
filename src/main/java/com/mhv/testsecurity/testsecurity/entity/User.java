package com.mhv.testsecurity.testsecurity.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userId;

    @Column(length = 30)
    String userName;

    @Column(unique = true, length = 15)
    String userUserName;

    @Column(length = 100)
    String userPassword;

    @Column(length = 15)
    String userRole;

    boolean userPasswordNonExpired;
    boolean userAccountNonLocked;
    boolean userAccountNonExpired;
    boolean userEnabled;

    public User() {
    }

    public User(int userId, String userName, String userUserName, String userPassword, String userRole, boolean userPasswordNonExpired, boolean userAccountNonLocked, boolean userAccountNonExpired, boolean userEnabled) {
        this.userId = userId;
        this.userName = userName;
        this.userUserName = userUserName;
        this.userRole = userRole;
        this.userPasswordNonExpired = userPasswordNonExpired;
        this.userAccountNonLocked = userAccountNonLocked;
        this.userAccountNonExpired = userAccountNonExpired;
        this.userEnabled = userEnabled;
        this.userPassword = userPassword;
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

    public String getUserUserName() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
                ", userUserName='" + userUserName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userPasswordNonExpired=" + userPasswordNonExpired +
                ", userAccountNonLocked=" + userAccountNonLocked +
                ", userAccountNonExpired=" + userAccountNonExpired +
                ", userEnabled=" + userEnabled +
                '}';
    }
}
