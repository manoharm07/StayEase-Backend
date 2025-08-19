package com.example.hostelManagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomVariableDto {

    @NotNull(message = "floorNo must not be null")
    @Min(1)
    private Integer floorNo;

    @NotNull(message = "roomNo must not be null")
    @Min(0)
    private Integer roomNo;

    @NotNull(message = "totalSeatsInRoom must not be null")
    @Min(0)
    private Integer totalSeatsInRoom;

}