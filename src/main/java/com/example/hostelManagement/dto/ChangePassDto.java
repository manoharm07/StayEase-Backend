package com.example.hostelManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class ChangePassDto {

    private String email;

    @NotBlank(message = "Password may not be blank")
    @Size(min=6,message = "Password must be at least 6 characters")
    private String oldPassword;

    @NotBlank(message = "Password may not be blank")
    @Size(min=6,message = "Password must be at least 6 characters")
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(@NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public @NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String newPassword) {
        this.newPassword = newPassword;
    }
}