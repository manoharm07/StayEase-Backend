package com.example.hostelManagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ApplicationId implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "hostel_id")
    private Integer hostelId;

}
