package com.example.hostelManagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomStuDto {

    @NotNull(message = "roomId must not be null")
    private Integer studentId;

    @NotBlank(message = "studentName must not be blank")
    private String studentName;
    
}