package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}