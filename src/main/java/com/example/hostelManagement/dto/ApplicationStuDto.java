package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ApplicationStuDto {

    @NotBlank(message = "hostelName must not be blank")
    private String hostelName;

    @NotNull(message = "std_id must not be null")
    private Status status;

    public ApplicationStuDto(String hostelName, Status status) {
        this.hostelName = hostelName;
        this.status = status;
    }

    public @NotBlank(message = "hostelName must not be blank") String getHostelName() {
        return hostelName;
    }

    public void setHostelName(@NotBlank(message = "hostelName must not be blank") String hostelName) {
        this.hostelName = hostelName;
    }

    public @NotNull(message = "std_id must not be null") Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull(message = "std_id must not be null") Status status) {
        this.status = status;
    }
}