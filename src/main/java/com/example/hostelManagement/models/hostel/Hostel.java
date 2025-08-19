package com.example.hostelManagement.models.hostel;

import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.FeesPayment;
import com.example.hostelManagement.models.user.Staff;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

}