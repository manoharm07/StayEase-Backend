package com.example.hostelManagement.dto;

import jakarta.validation.constraints.NotNull;

public class StdToRoomDto {

    @NotNull(message = "room_id must not be null")
    private Integer room_id;

    @NotNull(message = "std_id must not be null")
    private Integer std_id;

    public @NotNull(message = "room_id must not be null") Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(@NotNull(message = "room_id must not be null") Integer room_id) {
        this.room_id = room_id;
    }

    public @NotNull(message = "std_id must not be null") Integer getStd_id() {
        return std_id;
    }

    public void setStd_id(@NotNull(message = "std_id must not be null") Integer std_id) {
        this.std_id = std_id;
    }
}