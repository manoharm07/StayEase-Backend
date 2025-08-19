package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}