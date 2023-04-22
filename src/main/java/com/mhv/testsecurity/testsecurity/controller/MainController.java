package com.mhv.testsecurity.testsecurity.controller;

import com.mhv.testsecurity.testsecurity.entity.User;
import com.mhv.testsecurity.testsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/public/home")
    public ResponseEntity<String> publicHome(){
        return ResponseEntity.ok("Welcome to PUBLIC home page");
    }

    //use below to use method level authorisation
    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user/home")
    public ResponseEntity<String> userHome(){
        return ResponseEntity.ok("Welcome to USER home page");
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public ResponseEntity<String> adminHome(){
        return ResponseEntity.ok("Welcome to ADMIN home page");
    }

    @GetMapping("/admin/create-new-user")
    public ResponseEntity<User> createUser() {
        User user = new User();
        user.setUserName("Mahavir");
        user.setUserUserName("mhv");
        user.setUserRole("ROLE_USER");
        user.setUserPassword(bCryptPasswordEncoder.encode("1234"));
        if(userService.saveNewUser(user) != null )
            return ResponseEntity.ok(user);
        else
            return ResponseEntity.badRequest().build();
    }
}
