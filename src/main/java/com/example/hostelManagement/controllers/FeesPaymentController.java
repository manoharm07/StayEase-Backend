package com.example.hostelManagement.controllers;


import com.example.hostelManagement.dto.FeesPaymentDto;
import com.example.hostelManagement.dto.FeesStaffDto;
import com.example.hostelManagement.dto.FeesStudentDto;
import com.example.hostelManagement.dto.VerifyPaymentDto;
import com.example.hostelManagement.models.FeesPayment;
import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.FeesPaymentService;
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
@RequestMapping("feesPayment")
public class FeesPaymentController {

    @Autowired
    private FeesPaymentService feesPaymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostelService hostelService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/payFees")
    public ResponseEntity<?> payFees(@RequestBody FeesPaymentDto feesPaymentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;
        if(student.getRoom()==null){;
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized student");
        }
        feesPaymentService.payFees(student.getId(), student.getRoom().getHostel().getHostel_id(), feesPaymentDto.getAmount(), feesPaymentDto.getTransaction_id());
        return ResponseEntity.ok("fees paid successfully");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody VerifyPaymentDto verifyPaymentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        Optional<FeesPayment> feesPayment = feesPaymentService.getFeesPaymentById(verifyPaymentDto.getTransaction_id());
        if (feesPayment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid fees payment id");
        }
        if(feesPayment.get().getHostel() != staff.getHostel()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized fees payment");
        }

        feesPaymentService.verifyPayment(verifyPaymentDto.getTransaction_id(),verifyPaymentDto.getPaymentStatus());
        return ResponseEntity.ok("verified successfully");
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no hostel");
        }

        List<FeesStaffDto> allPaymentsForHostel = feesPaymentService.getAllPaymentsForHostel(staff.getHostel().getHostel_id());
        return ResponseEntity.ok(allPaymentsForHostel);
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

        List<FeesStudentDto> allPaymentsForStudent = feesPaymentService.getAllPaymentsForStudent(student.getId());
        return ResponseEntity.ok(allPaymentsForStudent);
    }
}