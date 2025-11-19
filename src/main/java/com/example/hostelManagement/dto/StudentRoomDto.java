package com.example.hostelManagement.dto;


import jakarta.validation.constraints.NotNull;

public class StudentRoomDto {

    @NotNull(message = "roomNo must not be null")
    private Integer roomNo;

    @NotNull(message = "floorNo must not be null")
    private Integer floorNo;

    public StudentRoomDto(Integer roomNo, Integer floorNo) {
        this.roomNo = roomNo;
        this.floorNo = floorNo;
    }

    public @NotNull(message = "roomNo must not be null") Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(@NotNull(message = "roomNo must not be null") Integer roomNo) {
        this.roomNo = roomNo;
    }

    public @NotNull(message = "floorNo must not be null") Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(@NotNull(message = "floorNo must not be null") Integer floorNo) {
        this.floorNo = floorNo;
    }
}