package com.mohammedismaiel.usermanagement.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mohammedismaiel.usermanagement.app.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUsername(String username) throws UsernameNotFoundException;
}
