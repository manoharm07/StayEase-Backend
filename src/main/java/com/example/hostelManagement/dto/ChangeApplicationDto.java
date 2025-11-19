package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.NotNull;
public class ChangeApplicationDto {

    @NotNull(message = "applicationId must not be null")
    private Integer applicationId;

    @NotNull(message = "status must not be null")
    private Status status;

    public @NotNull(message = "applicationId must not be null") Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(@NotNull(message = "applicationId must not be null") Integer applicationId) {
        this.applicationId = applicationId;
    }

    public @NotNull(message = "status must not be null") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "status must not be null") Status status) {
        this.status = status;
    }
}