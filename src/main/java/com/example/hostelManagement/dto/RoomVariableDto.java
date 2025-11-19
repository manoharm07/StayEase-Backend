package com.example.hostelManagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

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

    public @NotNull(message = "floorNo must not be null") @Min(1) Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(@NotNull(message = "floorNo must not be null") @Min(1) Integer floorNo) {
        this.floorNo = floorNo;
    }

    public @NotNull(message = "roomNo must not be null") @Min(0) Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(@NotNull(message = "roomNo must not be null") @Min(0) Integer roomNo) {
        this.roomNo = roomNo;
    }

    public @NotNull(message = "totalSeatsInRoom must not be null") @Min(0) Integer getTotalSeatsInRoom() {
        return totalSeatsInRoom;
    }

    public void setTotalSeatsInRoom(@NotNull(message = "totalSeatsInRoom must not be null") @Min(0) Integer totalSeatsInRoom) {
        this.totalSeatsInRoom = totalSeatsInRoom;
    }
}