package com.example.hostelManagement.models;

import com.example.hostelManagement.constants.Status;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "hostel_id"})
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Hostel hostel;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}