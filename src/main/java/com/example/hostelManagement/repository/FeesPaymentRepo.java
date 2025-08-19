package com.example.hostelManagement.repository;

import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.FeesPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FeesPaymentRepo extends JpaRepository<FeesPayment,Integer> {

    @Query("SELECT f FROM FeesPayment f WHERE f.transactionId = :transactionId")
    Optional<FeesPayment> findByTransactionId(@Param("transactionId") String transactionId);
}
