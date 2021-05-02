package com.example.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ValidateOwnerReqService {

    private final UserService userService;


    public boolean isAllowed(Long userId) {
        return userService.isOwner(userId) && userService.checkIfUserIsLoggedIn(userId);
    }
}
