package com.mohammedismaiel.usermanagement.app.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterData {
    private String username;
    private String name;
    private String password;
}
