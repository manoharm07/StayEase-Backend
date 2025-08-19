package com.example.hostelManagement.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRoomDto {

    @NotNull(message = "roomNo must not be null")
    private Integer roomNo;

    @NotNull(message = "floorNo must not be null")
    private Integer floorNo;


}