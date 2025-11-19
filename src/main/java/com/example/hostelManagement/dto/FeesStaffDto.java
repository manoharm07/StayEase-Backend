package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class FeesStaffDto {

    @NotNull(message = "amount must not be null")
    private Integer amount;

    @NotBlank(message = "amount must not be")
    private String studentName;

    @NotBlank(message = "amount must not be")
    private String email;

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

    @NotNull(message = "paymentStatus must not be null")
    private PaymentStatus paymentStatus;

    public FeesStaffDto(Integer amount, String studentName, String email, String transaction_id, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.studentName = studentName;
        this.email = email;
        this.transaction_id = transaction_id;
        this.paymentStatus = paymentStatus;
    }

    public @NotNull(message = "amount must not be null") Integer getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "amount must not be null") Integer amount) {
        this.amount = amount;
    }

    public @NotBlank(message = "amount must not be") String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NotBlank(message = "amount must not be") String studentName) {
        this.studentName = studentName;
    }

    public @NotBlank(message = "amount must not be") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "amount must not be") String email) {
        this.email = email;
    }

    public @NotBlank(message = "transaction_id must not be null") String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(@NotBlank(message = "transaction_id must not be null") String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public @NotNull(message = "paymentStatus must not be null") PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotNull(message = "paymentStatus must not be null") PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}