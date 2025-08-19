package com.example.hostelManagement.service;


import com.example.hostelManagement.constants.Status;
import com.example.hostelManagement.dto.ApplicationStaffDto;
import com.example.hostelManagement.dto.ApplicationStuDto;
import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.repository.ApplicationRepo;
import com.example.hostelManagement.repository.hostel.HostelRepo;
import com.example.hostelManagement.repository.user.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepo applicationRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private HostelRepo hostelRepo;

    public void createApplication(Integer studentId, Integer hostelId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Hostel> hostel = hostelRepo.findById(hostelId);

        Application application = new Application();

        if(student.isPresent() && hostel.isPresent()) {
            application.setStudent(student.get());
            application.setHostel(hostel.get());
            applicationRepo.save(application);
        }else{
            throw new RuntimeException("Either student or hostel not found!");
        }
    }

    public List<ApplicationStaffDto> getAllApplicationsForHostel(Integer hostelId) {
        Optional<Hostel> hostel = hostelRepo.findById(hostelId);
        return hostel.map(value -> applicationRepo.findAllByHostel(value).stream().map(application -> new ApplicationStaffDto(application.getStudent().getName(), application.getStatus(), application.getStudent().getEmail(), application.getId())).toList()).orElse(Collections.emptyList());
    }

    public List<ApplicationStuDto> getAllApplicationsForStudent(Integer studentId) {
        Optional<Student> student = studentRepo.findById(studentId);
        return student.map(value -> applicationRepo.findAllByStudent(value).stream().map(application -> new ApplicationStuDto(application.getHostel().getName(), application.getStatus())).toList()).orElse(Collections.emptyList());
    }

    public void changeApplicationStatus(Integer application_id, Status status) {
        System.out.println("hai how are ----------------");
        Optional<Application> application = applicationRepo.findById(application_id);

        if(application.isPresent()) {
            application.get().setStatus(status);
            applicationRepo.save(application.get());
        }
    }

    public Optional<Application> getApplicationById(Integer application_id) {
        return applicationRepo.findById(application_id);
    }

    public boolean checkBothPresent(Student student, Hostel hostel) {
        return applicationRepo.existsByStudentAndHostel(student, hostel);
    }


}