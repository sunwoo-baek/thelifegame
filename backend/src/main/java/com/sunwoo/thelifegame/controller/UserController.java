package com.sunwoo.thelifegame.controller;

import com.sunwoo.thelifegame.model.User;
import com.sunwoo.thelifegame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")  // base path for all user-related endpoints
public class UserController {

    private final UserService userService;

    @Autowired  // injects UserService into this controller
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // save user
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Update user
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get user by username
    @GetMapping("/by-username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get user by email
    @GetMapping("/by-email/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Delete user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}