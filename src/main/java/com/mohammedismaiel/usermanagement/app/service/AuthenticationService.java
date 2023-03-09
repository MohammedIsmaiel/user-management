package com.mohammedismaiel.usermanagement.app.service;

import com.mohammedismaiel.usermanagement.app.domain.Role;
import com.mohammedismaiel.usermanagement.app.domain.User;
import com.mohammedismaiel.usermanagement.app.domain.auth.LoginData;
import com.mohammedismaiel.usermanagement.app.domain.auth.RegisterData;
import com.mohammedismaiel.usermanagement.app.repository.UserRepository;
import com.mohammedismaiel.usermanagement.app.util.JWTTokenUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationService {

    private UserRepository userRepository;
    private JWTTokenUtil jwtTokenUtil;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public User login(LoginData loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getUsername(), loginData.getPassword()));
        var user = userRepository.findUserByUsername(loginData.getUsername());
        jwtTokenUtil.generateToken(user);
        return user;
    }

    public User register(RegisterData registerData, Role role) {
        var user = User.builder()
                .name(registerData.getName())
                .username(registerData.getUsername())
                .password(passwordEncoder.encode(registerData.getPassword()))
                .role(role)
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .build();
        return userRepository.save(user);
    }
}
