package com.example.hostelManagement.models.hostel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Food {

    @Id
    private Integer food_id;

    @OneToOne
    @JoinColumn(name = "hostel_id")
    @MapsId
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Hostel hostel;

    private Integer std_count;

    public Integer getFood_id() {
        return food_id;
    }

    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public Integer getStd_count() {
        return std_count;
    }

    public void setStd_count(Integer std_count) {
        this.std_count = std_count;
    }
}