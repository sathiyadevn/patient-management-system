package com.pm.authservice.dto;

public class LoginResponseDTO {

    // Using a constructor with a final field makes LoginResponseDTO immutable, safer, and clear
    // while a setter allows changes and can lead to inconsistent or insecure states.
    private final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
