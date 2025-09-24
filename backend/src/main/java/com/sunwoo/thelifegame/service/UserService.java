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

    // create or update user
    public User saveUser(User user) {
        return userRepository.save(user);
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

}
