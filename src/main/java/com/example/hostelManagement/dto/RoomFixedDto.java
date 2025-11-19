package com.example.hostelManagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    public @NotNull(message = "floorNo must not be null") @Min(1) Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(@NotNull(message = "floorNo must not be null") @Min(1) Integer floorNo) {
        this.floorNo = floorNo;
    }

    public @NotNull(message = "totalRooms must not be null") @Min(0) Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(@NotNull(message = "totalRooms must not be null") @Min(0) Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public @NotNull(message = "startRoomNo must not be null") @Min(0) Integer getStartRoomNo() {
        return startRoomNo;
    }

    public void setStartRoomNo(@NotNull(message = "startRoomNo must not be null") @Min(0) Integer startRoomNo) {
        this.startRoomNo = startRoomNo;
    }

    public @NotNull(message = "totalSeatsInEachRoom must not be null") @Min(0) Integer getTotalSeatsInEachRoom() {
        return totalSeatsInEachRoom;
    }

    public void setTotalSeatsInEachRoom(@NotNull(message = "totalSeatsInEachRoom must not be null") @Min(0) Integer totalSeatsInEachRoom) {
        this.totalSeatsInEachRoom = totalSeatsInEachRoom;
    }
}