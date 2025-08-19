package com.example.hostelManagement.service;


import com.example.hostelManagement.constants.PaymentStatus;
import com.example.hostelManagement.dto.FeesStaffDto;
import com.example.hostelManagement.dto.FeesStudentDto;
import com.example.hostelManagement.models.FeesPayment;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.repository.FeesPaymentRepo;
import com.example.hostelManagement.repository.hostel.HostelRepo;
import com.example.hostelManagement.repository.user.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FeesPaymentService {

    @Autowired
    private FeesPaymentRepo feesPaymentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private HostelRepo hostelRepo;

    public void payFees(Integer student_id, Integer hostel_id, Integer amount,String transaction_id) {
        FeesPayment feesPayment = new FeesPayment();
        feesPayment.setTransactionId(transaction_id);
        feesPayment.setHostel(hostelRepo.findById(hostel_id).orElse(null));
        feesPayment.setStudent(studentRepo.findById(student_id).orElse(null));
        feesPayment.setAmount(amount);
        feesPayment.setYear(LocalDate.now());
        feesPaymentRepo.save(feesPayment);
    }

    public void verifyPayment(String transaction_id, PaymentStatus paymentStatus) {
        FeesPayment feesPayment = feesPaymentRepo.findByTransactionId(transaction_id).orElse(null);
        if(feesPayment!= null) {
            feesPayment.setPaymentStatus(paymentStatus);
            feesPaymentRepo.save(feesPayment);
        }
    }

    public List<FeesStudentDto> getAllPaymentsForStudent(Integer student_id) {
        Optional<Student> student = studentRepo.findById(student_id);
        return student.map(value -> value.getFeesPayments().stream().map(feesPayment -> new FeesStudentDto(feesPayment.getAmount(), feesPayment.getTransactionId(),feesPayment.getYear(), feesPayment.getPaymentStatus())).toList()).orElse(Collections.emptyList());
    }

    public List<FeesStaffDto> getAllPaymentsForHostel(Integer hostel_id) {
        Optional<Hostel> hostel = hostelRepo.findById(hostel_id);
        return hostel.map(value -> value.getFeesPayments().stream().map(feesPayment -> new FeesStaffDto(feesPayment.getAmount(), feesPayment.getStudent().getName(), feesPayment.getStudent().getEmail(), feesPayment.getTransactionId(), feesPayment.getPaymentStatus())).toList()).orElse(Collections.emptyList());
    }

    public Optional<FeesPayment> getFeesPaymentById(String id) {
        return feesPaymentRepo.findByTransactionId(id);
    }
}