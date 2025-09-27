package com.sunwoo.thelifegame.service;

import com.sunwoo.thelifegame.model.User;
import com.sunwoo.thelifegame.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // save user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // update user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            if (updatedUser.getUsername() != null) user.setUsername(updatedUser.getUsername());
            if (updatedUser.getEmail() != null) user.setEmail(updatedUser.getEmail());
            if (updatedUser.getPasswordHash() != null) user.setPasswordHash(updatedUser.getPasswordHash());
            // Add any other fields you want to allow updating

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // find user by id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}