package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationStuDto {

    @NotBlank(message = "hostelName must not be blank")
    private String hostelName;

    @NotNull(message = "std_id must not be null")
    private Status status;
}