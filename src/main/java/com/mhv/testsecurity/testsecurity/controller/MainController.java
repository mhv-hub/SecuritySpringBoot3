package com.mhv.testsecurity.testsecurity.controller;

import com.mhv.testsecurity.testsecurity.config.CustomUserDetailsService;
import com.mhv.testsecurity.testsecurity.entity.User;
import com.mhv.testsecurity.testsecurity.model.AuthenticationRequest;
import com.mhv.testsecurity.testsecurity.model.AuthenticationResponse;
import com.mhv.testsecurity.testsecurity.service.UserService;
import com.mhv.testsecurity.testsecurity.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    TokenUtility tokenUtility;

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

    @PostMapping("/public/authenticate")
    public String authenticateAndCreateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (Exception e){
            throw new Exception("User name or password is incorrect !!", e);
        }
        finally {
            if(authenticate != null && authenticate.isAuthenticated()) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
                String token = tokenUtility.generateToken(userDetails);
                return token;
            }
            else{
                return "User name or password is incorrect !!";
            }
        }
    }
}
