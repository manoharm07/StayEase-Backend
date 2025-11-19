package com.example.hostelManagement.models.user;

import com.example.hostelManagement.constants.Position;
import com.example.hostelManagement.models.hostel.Hostel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Staff extends User {

    @Column
    private Position position;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonBackReference
    private Hostel hostel;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
