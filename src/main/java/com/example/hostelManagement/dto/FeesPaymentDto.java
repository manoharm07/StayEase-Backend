package com.example.hostelManagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesPaymentDto {

    @NotNull(message = "amount must not be null")
    private Integer amount;

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

}