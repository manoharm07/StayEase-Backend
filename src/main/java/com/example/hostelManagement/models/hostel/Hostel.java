package com.example.hostelManagement.models.hostel;

import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.FeesPayment;
import com.example.hostelManagement.models.user.Staff;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hostel_id;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private Integer empty_seats;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column
    private String phone;

    @Column(nullable = false)
    private Integer fees;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.REMOVE)
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Staff> staffs = new ArrayList<>();

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<FeesPayment> feesPayments = new ArrayList<>();

    @OneToOne(mappedBy = "hostel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Food food;

    public Integer getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(Integer hostel_id) {
        this.hostel_id = hostel_id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getEmpty_seats() {
        return empty_seats;
    }

    public void setEmpty_seats(Integer empty_seats) {
        this.empty_seats = empty_seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<FeesPayment> getFeesPayments() {
        return feesPayments;
    }

    public void setFeesPayments(List<FeesPayment> feesPayments) {
        this.feesPayments = feesPayments;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}