package com.example.hostelManagement.models.hostel;

import com.example.hostelManagement.models.user.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "room_id")
    private Integer roomId;

    @Column(nullable = false,name = "room_no")
    private Integer roomNo;

    @Column(nullable = false,name = "floor_no")
    private Integer floorNo;

    @Column(nullable = false,name = "total_seats")
    private Integer totalSeats;

    @Column(nullable = false,name = "empty_seats")
    private Integer emptySeats;

    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Hostel hostel = new Hostel();

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    public Integer getEmptySeats() {
        return totalSeats - students.size();
    }

}