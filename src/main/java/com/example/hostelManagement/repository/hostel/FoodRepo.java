package com.example.hostelManagement.repository.hostel;

import com.example.hostelManagement.models.hostel.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<Food,Integer> {
}
