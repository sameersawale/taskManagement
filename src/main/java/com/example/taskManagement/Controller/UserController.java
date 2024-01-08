package com.example.taskManagement.Controller;

import com.example.taskManagement.Dto.LoginDto;
import com.example.taskManagement.Dto.LoginResponse;
import com.example.taskManagement.Model.User;
import com.example.taskManagement.Security.JwtTokenProvider;
import com.example.taskManagement.Service.Impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);


        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @RequestBody LoginDto loginDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") int id) throws Exception {
        return userService.getUserById(id);
    }

    @GetMapping("/get")
    public User currentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
