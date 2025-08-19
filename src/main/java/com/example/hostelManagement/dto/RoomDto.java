package com.example.hostelManagement.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    @NotNull(message = "roomId must not be null")
    private Integer roomId;

    @NotNull(message = "roomNo must not be null")
    @Min(1)
    private Integer roomNo;

    @NotNull(message = "floorNo must not be null")
    @Min(1)
    private Integer floorNo;

    @NotNull(message = "totalSeats must not be null")
    @Min(0)
    private Integer totalSeats;

    @NotNull(message = "emptySeats must not be null")
    @Min(0)
    private Integer emptySeats;
    
}