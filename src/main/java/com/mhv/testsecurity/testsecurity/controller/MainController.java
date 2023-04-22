package com.mhv.testsecurity.testsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

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
}
