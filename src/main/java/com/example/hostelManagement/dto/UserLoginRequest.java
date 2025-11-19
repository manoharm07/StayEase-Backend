package com.example.hostelManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequest {

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password may not be blank")
    @Size(min=6,message = "Password must be at least 6 characters")
    private String password;

    public @NotBlank(message = "Email may not be blank") @Email(message = "Email must be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email may not be blank") @Email(message = "Email must be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }
}
