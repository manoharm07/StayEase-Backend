package com.example.hostelManagement.controllers.hostel;


import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.hostel.Food;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.hostel.FoodService;
import com.example.hostelManagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/get")
    public ResponseEntity<?> getFood() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        Optional<Food> food = foodService.getFood(staff.getHostel().getHostel_id());
        if(food.isPresent()){
            return ResponseEntity.ok(food.get().getStd_count());
        }
        return ResponseEntity.ok("not found");
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PutMapping("/incCount")
    public ResponseEntity<String> incrementCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;
        foodService.incrementCount(student);
        return ResponseEntity.ok("added your count");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/clearCount")
    public ResponseEntity<String> clearCount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        foodService.clearCount(staff);
        return ResponseEntity.ok("cleared count");
    }

}