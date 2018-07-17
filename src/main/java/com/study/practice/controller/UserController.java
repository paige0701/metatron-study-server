package com.study.practice.controller;

import com.study.practice.repository.User;
import com.study.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        System.out.println("Print users");
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") String userId) {

        return userRepository.getOne(userId);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") String userId,
                           @Valid @RequestBody User userDetails) {

        User user = userRepository.getOne(userId);

        user.setName(userDetails.getName());
        user.setCode(userDetails.getCode());

        User updateUser = userRepository.save(user);
        return updateUser;
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") String userId) {
        User user = userRepository.getOne(userId);

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

}
