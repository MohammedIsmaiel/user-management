package com.mohammedismaiel.usermanagement.app.resource;

import com.mohammedismaiel.usermanagement.app.domain.Role;
import com.mohammedismaiel.usermanagement.app.domain.auth.HttpResponse;
import com.mohammedismaiel.usermanagement.app.domain.auth.LoginData;
import com.mohammedismaiel.usermanagement.app.domain.auth.RegisterData;
import com.mohammedismaiel.usermanagement.app.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthenticationResource {
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody LoginData loginData) {
        authenticationService.login(loginData);
        return ResponseEntity.ok().body(new HttpResponse());
    }


    @PostMapping("/{role}/register")
    public ResponseEntity<HttpResponse> register(@RequestBody RegisterData registerData, @RequestParam("role") Role role) {
        authenticationService.register(registerData, role);
        return ResponseEntity.ok().body(new HttpResponse());
    }
}
