package com.example.hostelManagement.controllers.auth;


import com.example.hostelManagement.constants.Role;
import com.example.hostelManagement.dto.ChangePassDto;
import com.example.hostelManagement.dto.ProfileDto;
import com.example.hostelManagement.dto.UserLoginRequest;
import com.example.hostelManagement.dto.UserRegisterRequest;
import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.auth.JwtService;
import com.example.hostelManagement.service.user.StaffService;
import com.example.hostelManagement.service.user.StudentService;
import com.example.hostelManagement.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest userRequest) {
        if (userService.findByEmail(userRequest.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("already registered user");
        }

        try {
            User user = null;
            switch (userRequest.getRole()) {
                case STAFF -> {
                    Staff staff = getStaff(userRequest);
                    user = staffService.createStaff(staff);
                }
                case STUDENT -> {
                    Student student = getStudent(userRequest);
                    user = studentService.createStudent(student);
                }
            }
            userService.createUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest userRequest,HttpServletResponse response) {
        User user = userService.findByEmail(userRequest.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(userRequest.getEmail());
                Cookie cookie = new Cookie("jwt", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
                return ResponseEntity.ok(Map.of(
                        "role", user.getRole()
                ));
            }
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error: " + ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password not match");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("logout successful");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserPrincipal userDetails) {
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(getProfileFromUser(user));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePassDto changePassDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        String email = userDetails.getUsername();
        changePassDto.setEmail(email);
        try {
            userService.changePassword(changePassDto);
            return ResponseEntity.ok("password changed");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");
        } catch (InputMismatchException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("password do not match");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        String email = userDetails.getUsername();

        userService.deleteUserByEmail(email);
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("user deleted");
    }

    @GetMapping("/getUserMode")
    public ResponseEntity<?> getUserCapability() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
            String email = userDetails.getUsername();
            User user = userService.findByEmail(email);

            if (user != null) {
                if (user.getRole() == Role.STUDENT) {
                    Student student = (Student) user;
                    if(student.getRoom()==null) {
                        return ResponseEntity.ok("STUDENT");
                    }else{
                        return ResponseEntity.ok("USTUDENT");
                    }
                } else if (user.getRole() == Role.STAFF) {
                    Staff staff = (Staff) user;
                    if(staff.getHostel()==null) {
                        return ResponseEntity.ok("STAFF");
                    }else{
                        return ResponseEntity.ok("USTAFF");
                    }
                }
            }
        } else{
            return ResponseEntity.ok("USER");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("bad request");
    }

    private static ProfileDto getProfileFromUser(User user) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(user.getEmail());
        profileDto.setName(user.getName());
        profileDto.setAddress(user.getAddress());
        profileDto.setPhone(user.getPhone());
        profileDto.setRole(user.getRole());
        return profileDto;
    }


    private static Staff getStaff(UserRegisterRequest userRequest) {
        Staff staff = new Staff();
        staff.setEmail(userRequest.getEmail());
        staff.setName(userRequest.getName());
        staff.setPassword(userRequest.getPassword());
        staff.setAddress(userRequest.getAddress());
        staff.setPhone(userRequest.getPhone());
        staff.setRole(userRequest.getRole());
        staff.setPosition(userRequest.getPosition());
        return staff;
    }

    private static Student getStudent(UserRegisterRequest userRequest) {
        Student student = new Student();
        student.setEmail(userRequest.getEmail());
        student.setName(userRequest.getName());
        student.setPassword(userRequest.getPassword());
        student.setPhone(userRequest.getPhone());
        student.setRole(userRequest.getRole());
        student.setAddress(userRequest.getAddress());
        return student;
    }

}