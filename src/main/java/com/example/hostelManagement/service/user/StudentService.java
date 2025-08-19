package com.example.hostelManagement.service.user;


import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.repository.user.StudentRepo;
import com.example.hostelManagement.repository.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public User createStudent(Student student) {
        return studentRepo.save(student);
    }
}