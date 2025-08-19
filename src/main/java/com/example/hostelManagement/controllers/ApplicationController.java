package com.example.hostelManagement.controllers;


import com.example.hostelManagement.dto.ApplicationStaffDto;
import com.example.hostelManagement.dto.ApplicationStuDto;
import com.example.hostelManagement.dto.ChangeApplicationDto;
import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.ApplicationService;
import com.example.hostelManagement.service.hostel.HostelService;
import com.example.hostelManagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private HostelService hostelService;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/create/{hostelId}")
    public ResponseEntity<?> createApplication(@PathVariable("hostelId") Integer hostelId) {
        Hostel hostel = hostelService.getHostelById(hostelId);
        if(hostel==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("hostel not found");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;
        applicationService.createApplication(student.getId(),hostelId);
        return ResponseEntity.ok("application created successfully");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/getForHostel")
    public ResponseEntity<?> getAllApplicationsForHostel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        if (staff.getHostel() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("hostel not found");
        }
        List<ApplicationStaffDto> applications = applicationService.getAllApplicationsForHostel(staff.getHostel().getHostel_id());
        return ResponseEntity.ok(applications);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/getForStudent")
    public ResponseEntity<?> getAllApplicationsForStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;

        List<ApplicationStuDto> applications = applicationService.getAllApplicationsForStudent(student.getId());
        return ResponseEntity.ok(applications);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/changeStatus")
    public ResponseEntity<?> changeApplicationStatus(@RequestBody ChangeApplicationDto applicationDto){
        System.out.println(applicationDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        Optional<Application> application = applicationService.getApplicationById(applicationDto.getApplicationId());
        if (application.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized application");
        }
        if (application.get().getHostel() != staff.getHostel()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized");
        }
        applicationService.changeApplicationStatus(applicationDto.getApplicationId(), applicationDto.getStatus());
        return ResponseEntity.ok("successfully updated status");
    }

    @GetMapping("/checkApplied/{id}")
    public ResponseEntity<?> checkStuApplied(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;

        Hostel hostel = hostelService.getHostelById(id);
        if (hostel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("hostel not found");
        }
        boolean check = applicationService.checkBothPresent(student,hostel);
        System.out.println(check);
        return ResponseEntity.ok(check);
    }
}