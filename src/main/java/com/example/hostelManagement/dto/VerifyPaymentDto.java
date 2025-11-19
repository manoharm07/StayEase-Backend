package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerifyPaymentDto {

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

    @NotNull(message = "paymentStatus must not be null")
    private PaymentStatus paymentStatus;

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