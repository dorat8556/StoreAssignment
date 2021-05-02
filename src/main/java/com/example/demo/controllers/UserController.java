package com.example.demo.controllers;

import com.example.demo.jpa.entities.UserEntity;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/login")
    public Long logInUser(@RequestParam Long userId) {
        return userService.logInOutUser(userId, true);
    }

    @PostMapping("/logout")
    public Long logUserOut(@RequestBody Long userId) {
        return userService.logInOutUser(userId, false);
    }

}
