package com.example.hostelManagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFixedDto {

    @NotNull(message = "floorNo must not be null")
    @Min(1)
    private Integer floorNo;

    @NotNull(message = "totalRooms must not be null")
    @Min(0)
    private Integer totalRooms;

    @NotNull(message = "startRoomNo must not be null")
    @Min(0)
    private Integer startRoomNo;

    @NotNull(message = "totalSeatsInEachRoom must not be null")
    @Min(0)
    private Integer totalSeatsInEachRoom;

}