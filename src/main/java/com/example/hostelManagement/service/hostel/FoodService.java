package com.example.hostelManagement.service.hostel;


import com.example.hostelManagement.models.hostel.Food;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.repository.hostel.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepo foodRepo;


    public Optional<Food> getFood(Integer hostelId){
        return foodRepo.findById(hostelId);
    }

    public void incrementCount(Student student) {
        Optional<Food> food = foodRepo.findById(student.getRoom().getHostel().getHostel_id());

        if(food.isPresent()){
            Food food1 = food.get();
            food1.setStd_count(food1.getStd_count() + 1);
            foodRepo.save(food1);
        }

    }
    public void clearCount(Staff staff) {
        Optional<Food> food = foodRepo.findById(staff.getHostel().getHostel_id());

        if(food.isPresent()){
            Food food1 = food.get();
            food1.setStd_count(0);
            foodRepo.save(food1);
        }
    }
}