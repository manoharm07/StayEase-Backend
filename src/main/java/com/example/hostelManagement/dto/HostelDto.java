package com.example.hostelManagement.dto;

import jakarta.validation.constraints.*;
public class HostelDto {

    private Integer hostelId;

    @NotNull(message = "capacity must not be null")
    @Min(1)
    private Integer capacity;

    @NotNull(message = "empty_seats must not be null")
    @Min(0)
    private Integer empty_seats;

    @NotBlank(message = "hostel name may not be blank")
    private String name;

    @NotBlank(message = "hostel address may not be blank")
    private String location;

    @NotBlank(message = "hostel phone no. may not be blank")
    private String phone;

    @NotNull(message = "fees must not be null")
    @Min(0)
    private Integer fees;

    public Integer getHostelId() {
        return hostelId;
    }

    public void setHostelId(Integer hostelId) {
        this.hostelId = hostelId;
    }

    public @NotNull(message = "capacity must not be null") @Min(1) Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NotNull(message = "capacity must not be null") @Min(1) Integer capacity) {
        this.capacity = capacity;
    }

    public @NotNull(message = "empty_seats must not be null") @Min(0) Integer getEmpty_seats() {
        return empty_seats;
    }

    public void setEmpty_seats(@NotNull(message = "empty_seats must not be null") @Min(0) Integer empty_seats) {
        this.empty_seats = empty_seats;
    }

    public @NotBlank(message = "hostel name may not be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "hostel name may not be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "hostel address may not be blank") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "hostel address may not be blank") String location) {
        this.location = location;
    }

    public @NotBlank(message = "hostel phone no. may not be blank") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "hostel phone no. may not be blank") String phone) {
        this.phone = phone;
    }

    public @NotNull(message = "fees must not be null") @Min(0) Integer getFees() {
        return fees;
    }

    public void setFees(@NotNull(message = "fees must not be null") @Min(0) Integer fees) {
        this.fees = fees;
    }
}