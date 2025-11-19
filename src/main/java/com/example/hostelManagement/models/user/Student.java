package com.example.hostelManagement.models.user;

import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.FeesPayment;
import com.example.hostelManagement.models.hostel.Room;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "room_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonManagedReference
    private Room room;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<FeesPayment> feesPayments = new ArrayList<>();

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<FeesPayment> getFeesPayments() {
        return feesPayments;
    }

    public void setFeesPayments(List<FeesPayment> feesPayments) {
        this.feesPayments = feesPayments;
    }
}
