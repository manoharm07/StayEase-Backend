package com.example.hostelManagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StdToRoomDto {

    @NotNull(message = "room_id must not be null")
    private Integer room_id;

    @NotNull(message = "std_id must not be null")
    private Integer std_id;
}