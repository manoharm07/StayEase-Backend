package com.example.hostelManagement.models.hostel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

}