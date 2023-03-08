package com.mohammedismaiel.usermanagement.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mohammedismaiel.usermanagement.app.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
