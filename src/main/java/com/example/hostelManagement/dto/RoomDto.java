package com.example.hostelManagement.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    public RoomDto(Integer roomId, Integer roomNo, Integer floorNo, Integer totalSeats, Integer emptySeats) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.floorNo = floorNo;
        this.totalSeats = totalSeats;
        this.emptySeats = emptySeats;
    }

    public @NotNull(message = "roomId must not be null") Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(@NotNull(message = "roomId must not be null") Integer roomId) {
        this.roomId = roomId;
    }

    public @NotNull(message = "roomNo must not be null") @Min(1) Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(@NotNull(message = "roomNo must not be null") @Min(1) Integer roomNo) {
        this.roomNo = roomNo;
    }

    public @NotNull(message = "floorNo must not be null") @Min(1) Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(@NotNull(message = "floorNo must not be null") @Min(1) Integer floorNo) {
        this.floorNo = floorNo;
    }

    public @NotNull(message = "totalSeats must not be null") @Min(0) Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(@NotNull(message = "totalSeats must not be null") @Min(0) Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public @NotNull(message = "emptySeats must not be null") @Min(0) Integer getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(@NotNull(message = "emptySeats must not be null") @Min(0) Integer emptySeats) {
        this.emptySeats = emptySeats;
    }
}