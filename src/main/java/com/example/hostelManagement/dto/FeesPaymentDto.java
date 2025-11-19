package com.example.hostelManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class FeesPaymentDto {

    @NotNull(message = "amount must not be null")
    private Integer amount;

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

    public FeesPaymentDto(Integer amount, String transaction_id) {
        this.amount = amount;
        this.transaction_id = transaction_id;
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
}