package com.example.demo.services;

import com.example.demo.jpa.entities.UserEntity;
import com.example.demo.jpa.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserJpaRepository userRepository;

    public boolean isOwner(Long userId) {
        return userRepository.findById(userId).isPresent() && userRepository.findById(userId).get().isOwner();
    }

    public Boolean checkIfUserIsLoggedIn(Long userId) {
        return userRepository.findById(userId).isPresent() && userRepository.findById(userId).get().isLoggedIn();
    }

    public UserEntity createUser(UserEntity newUser) {
        List<UserEntity> users = userRepository.findAll();
        log.info("New user: {}", newUser);
        for (UserEntity user : users) {
            if (user.equals(newUser)) {
                return user;
            }
        }
        userRepository.save(newUser);
        log.info("Registered user: {}", newUser);
        return newUser;
    }

    public Long logInOutUser(Long userId,boolean logIn) {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity other : users) {
            if (other.getId() == userId) {
                other.setLoggedIn(logIn);
                userRepository.save(other);
                return other.getId();
            }
        }
        return 0l;
    }
}
