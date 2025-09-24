package com.sunwoo.thelifegame.repository;

import com.sunwoo.thelifegame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // optional helps with null checks
    Optional<User> findByEmail(String email);
}
