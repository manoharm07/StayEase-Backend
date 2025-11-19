package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FeesStudentDto {

    @NotNull(message = "amount must not be null")
    private Integer amount;

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

    @NotNull(message = "date must not be null")
    private LocalDate date;

    @NotNull(message = "paymentStatus must not be null")
    private PaymentStatus paymentStatus;

    public FeesStudentDto(Integer amount, String transaction_id, LocalDate date, PaymentStatus paymentStatus) {
        this.amount = amount;
        this.transaction_id = transaction_id;
        this.date = date;
        this.paymentStatus = paymentStatus;
    }

    public @NotNull(message = "amount must not be null") Integer getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "amount must not be null") Integer amount) {
        this.amount = amount;
    }

    public @NotBlank(message = "transaction_id must not be null") String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(@NotBlank(message = "transaction_id must not be null") String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public @NotNull(message = "date must not be null") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "date must not be null") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "paymentStatus must not be null") PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotNull(message = "paymentStatus must not be null") PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}