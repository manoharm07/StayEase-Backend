package com.example.hostelManagement.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelDto {

    private Integer hostelId;

    @NotNull(message = "capacity must not be null")
    @Min(1)
    private Integer capacity;

    @NotNull(message = "empty_seats must not be null")
    @Min(0)
    private Integer empty_seats;

    @NotBlank(message = "hostel name may not be blank")
    private String name;

    @NotBlank(message = "hostel address may not be blank")
    private String location;

    @NotBlank(message = "hostel phone no. may not be blank")
    private String phone;

    @NotNull(message = "fees must not be null")
    @Min(0)
    private Integer fees;
}