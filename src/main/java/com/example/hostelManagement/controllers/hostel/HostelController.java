package com.example.hostelManagement.controllers.hostel;


import com.example.hostelManagement.constants.Role;
import com.example.hostelManagement.dto.HostelDto;
import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.hostel.Food;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.hostel.HostelService;
import com.example.hostelManagement.service.user.StaffService;
import com.example.hostelManagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hostel")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;


    @GetMapping("/details/{id}")
    public ResponseEntity<?> getHostelDetails(@PathVariable("id") Integer id) {
        Hostel hostel = hostelService.getHostelById(id);
        if (hostel == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("hostel not found");
        }
        return ResponseEntity.ok(getDtoFromHostel(hostel, new HostelDto()));
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getHostelDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        if (user.getRole() == Role.STUDENT) {
            Student student = (Student) user;
            if (student.getRoom() == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("hostel not exists");
            }
            return ResponseEntity.ok(student.getRoom().getHostel().getHostel_id());
        } else if (user.getRole() == Role.STAFF) {
            Staff staff = (Staff) user;
            if (staff.getHostel() == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("hostel not exists");
            }
            return ResponseEntity.ok(staff.getHostel().getHostel_id());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PostMapping("/create")
    public ResponseEntity<?> createHostel(@RequestBody HostelDto hostelDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }

        Staff staff = (Staff) user;
        if (staff.getHostel() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("hostel already exists");
        }
        Hostel hostel = getHostelFromDto(hostelDto, new Hostel());
        List<Staff> staffs = hostel.getStaffs();
        staffs.add(staff);
        hostel.setStaffs(staffs);
        hostel = hostelService.createHostel(hostel);
        Food food = new Food();
        food.setHostel(hostel);
        food.setStd_count(0);
        hostel.setFood(food);
        hostel = hostelService.updateHostel(hostel);
        staff.setHostel(hostel);
        staffService.createStaff(staff);
        return ResponseEntity.ok(getDtoFromHostel(hostel, new HostelDto()));
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/update")
    public ResponseEntity<?> updateHostel(@RequestBody HostelDto hostelDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        if (staff.getHostel() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("hostel not exists");
        }
        Hostel hostel = staff.getHostel();

        hostel = hostelService.updateHostel(getHostelFromDto(hostelDto, hostel));
        return ResponseEntity.ok(getDtoFromHostel(hostel, new HostelDto()));
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHostel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Staff staff = (Staff) user;
        Hostel hostel = staff.getHostel();
        List<Staff> hostelStaff = hostel.getStaffs();
        for (Staff s : hostelStaff) {
            s.setHostel(null);
            staffService.createStaff(s);
        }
        hostelService.deleteHostel(hostel.getHostel_id());
        return ResponseEntity.ok("deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchHostel(@RequestParam(value = "filterBy", required = false) String filterBy, @RequestParam(value = "searchText", required = false) String searchText) {

        List<Hostel> searchResults;

        if (filterBy != null && searchText != null && !searchText.isEmpty()) {
            searchResults = hostelService.getHostelsByFilter(filterBy, searchText);
        } else {
            searchResults = hostelService.getAllHostels();
        }
        List<HostelDto> hostelDtos = searchResults.stream().map(hostel -> getDtoFromHostel(hostel, new HostelDto())).toList();
        return ResponseEntity.ok(hostelDtos);
    }

    private Hostel getHostelFromDto(HostelDto hostelDto, Hostel hostel) {
        hostel.setFees(hostelDto.getFees());
        hostel.setCapacity(hostelDto.getCapacity());
        hostel.setEmpty_seats(hostelDto.getEmpty_seats());
        hostel.setName(hostelDto.getName());
        hostel.setPhone(hostelDto.getPhone());
        hostel.setLocation(hostelDto.getLocation());
        return hostel;
    }

    private HostelDto getDtoFromHostel(Hostel hostel, HostelDto hostelDto) {
        hostelDto.setHostelId(hostel.getHostel_id());
        hostelDto.setFees(hostel.getFees());
        hostelDto.setCapacity(hostel.getCapacity());
        hostelDto.setEmpty_seats(hostel.getEmpty_seats());
        hostelDto.setName(hostel.getName());
        hostelDto.setPhone(hostel.getPhone());
        hostelDto.setLocation(hostel.getLocation());
        return hostelDto;
    }

}