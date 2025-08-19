package com.example.hostelManagement.dto;

import com.example.hostelManagement.constants.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeesStudentDto {

    @NotNull(message = "amount must not be null")
    private Integer amount;

    @NotBlank(message = "transaction_id must not be null")
    private String transaction_id;

    @NotNull(message = "date must not be null")
    private LocalDate date;

    @NotNull(message = "paymentStatus must not be null")
    private PaymentStatus paymentStatus;

}