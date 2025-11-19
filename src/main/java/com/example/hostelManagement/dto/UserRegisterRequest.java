package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Position;
import com.example.hostelManagement.constants.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Name may not be blank")
    private String name;

    @NotBlank(message = "address may not be blank")
    private String address;

    @NotBlank(message = "Password may not be blank")
    @Size(min=6,message = "Password must be at least 6 characters")
    private String password;

    private String phone;

    @NotNull(message = "Please select role")
    private Role role;


    private Position position;

    public @NotBlank(message = "Email may not be blank") @Email(message = "Email must be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email may not be blank") @Email(message = "Email must be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Name may not be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name may not be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "address may not be blank") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "address may not be blank") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password may not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public @NotNull(message = "Please select role") Role getRole() {
        return role;
    }

    public void setRole(@NotNull(message = "Please select role") Role role) {
        this.role = role;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}