package com.example.hostelManagement.service.user;


import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.repository.user.StaffRepo;
import com.example.hostelManagement.repository.user.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public User createStaff(Staff staff) {
        return staffRepo.save(staff);
    }

}