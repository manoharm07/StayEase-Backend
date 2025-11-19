package com.example.hostelManagement.models;

import com.example.hostelManagement.constants.PaymentStatus;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "hostel_id", "year"}))
public class FeesPayment {

    @Id
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonManagedReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonManagedReference
    private Hostel hostel;

    private LocalDate year;

    private Integer amount;

    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}