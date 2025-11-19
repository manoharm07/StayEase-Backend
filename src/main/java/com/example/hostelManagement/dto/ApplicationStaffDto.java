package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ApplicationStaffDto {

    @NotBlank(message = "student_name must not be blank")
    private String studentName;

    @NotNull(message = "status must not be null")
    private Status status;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "applicationId must not be null")
    private Integer applicationId;

    public ApplicationStaffDto(String studentName, Status status, String email, Integer applicationId) {
        this.studentName = studentName;
        this.status = status;
        this.email = email;
        this.applicationId = applicationId;
    }

    public @NotBlank(message = "student_name must not be blank") String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NotBlank(message = "student_name must not be blank") String studentName) {
        this.studentName = studentName;
    }

    public @NotNull(message = "status must not be null") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "status must not be null") Status status) {
        this.status = status;
    }

    public @NotBlank(message = "email must not be blank") @Email(message = "Email must be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "email must not be blank") @Email(message = "Email must be valid") String email) {
        this.email = email;
    }

    public @NotNull(message = "applicationId must not be null") Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(@NotNull(message = "applicationId must not be null") Integer applicationId) {
        this.applicationId = applicationId;
    }
}