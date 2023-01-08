package com.example.nba.controller;

import com.example.nba.exception.AppException;
import com.example.nba.model.*;
import com.example.nba.payload.ApiResponse;
import com.example.nba.payload.JwtAuthenticationResponse;
import com.example.nba.payload.LoginRequest;
import com.example.nba.payload.SignUpRequest;
import com.example.nba.repository.RoleRepository;
import com.example.nba.repository.UserRepository;
import com.example.nba.security.AuthenticationFilter;
import com.example.nba.security.JwtTokenProvider;
import com.example.nba.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")

public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    AuthenticationFilter authenticationFilter;
   /* @Autowired
    private JWTUtility jwtUtil;
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsernameOrEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsernameOrEmail());
    }*/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        tokenProvider.validateToken(jwt);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(100, TimeUnit.SECONDS)).body(jwt);
    }

   /* @PostMapping("/signin")
    public void Fakelogin(@RequestBody User user){

    }*/
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole= roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        Set<Role> role=user.getRoles();
        for (Role s : role){
            System.out.println(s.getName());
           userRole = roleRepository.findByName(s.getName())
                    .orElseThrow(() -> new AppException("User Role not set."));
        }

        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }
   /* @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
       /* if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }*/

        // Creating user's account
        /*User user = new User(signUpRequest.getName(), signUpRequest.getUsername()
                , signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /*Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));


        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }*/
}
