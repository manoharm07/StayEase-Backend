package com.example.hostelManagement.repository.user;

import com.example.hostelManagement.models.user.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepository<Staff,Integer> {
}
