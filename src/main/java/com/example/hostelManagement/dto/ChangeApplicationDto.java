package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeApplicationDto {

    @NotNull(message = "applicationId must not be null")
    private Integer applicationId;

    @NotNull(message = "status must not be null")
    private Status status;
}