package com.example.hostelManagement.controllers.hostel;


import com.example.hostelManagement.dto.*;
import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.auth.UserPrincipal;
import com.example.hostelManagement.models.hostel.Room;
import com.example.hostelManagement.models.user.Staff;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.models.user.User;
import com.example.hostelManagement.service.hostel.HostelService;
import com.example.hostelManagement.service.hostel.RoomService;
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
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HostelService hostelService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/byFloor/{floorNo}")
    public ResponseEntity<?> getRoomsByFloor(@PathVariable Integer floorNo) {
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
        return ResponseEntity.ok(roomService.getRoomsByHostelAndFloor(staff.getHostel(), floorNo));
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/details/student")
    public ResponseEntity<?> getRoomForStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }
        Student student = (Student) user;
        if (student.getRoom() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("room not exists");
        }
        return ResponseEntity.ok(new StudentRoomDto(student.getRoom().getRoomNo(),
                student.getRoom().getFloorNo()
        ));
    }


    @PreAuthorize("hasAuthority('STAFF')")
    @GetMapping("/getStudents/withoutRoom")
    public ResponseEntity<?> getAllStudentsWithOutRoom() {
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
        List<Application> applications = staff.getHostel().getApplications();
        List<RoomStuDto> students = applications.stream()
                .map(Application::getStudent)
                .filter(student -> student != null && student.getRoom() == null)
                .map(student -> new RoomStuDto(student.getId(),student.getName()))
                .toList();
        return ResponseEntity.ok(students);
    }


    @PreAuthorize("hasAuthority('STAFF')")
    @PostMapping("/create/fixedSize")
    public ResponseEntity<?> createRoomsForFixedSize(@RequestBody RoomFixedDto roomFixedDto) {

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

        roomService.createRoomsForFixedSize(roomFixedDto.getFloorNo(), roomFixedDto.getTotalRooms(), roomFixedDto.getStartRoomNo(), staff.getHostel(), roomFixedDto.getTotalSeatsInEachRoom());
        return ResponseEntity.ok("rooms created successfully");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody RoomVariableDto roomVariableDto) {

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
        roomService.createRoom(roomVariableDto.getFloorNo(), roomVariableDto.getRoomNo(), staff.getHostel(), roomVariableDto.getTotalSeatsInRoom());
        return ResponseEntity.ok("rooms created successfully");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudentToRoom(@RequestBody StdToRoomDto stdToRoomDto) {
        Optional<Room> room = roomService.getRoom(stdToRoomDto.getRoom_id());
        if (room.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("room not found");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }

        Staff staff = (Staff) user;
        if (staff.getHostel() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("hostel not exists");
        }
        if (!staff.getHostel().equals(room.get().getHostel())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("hostel is not yours");
        }
        User student = userService.findById(stdToRoomDto.getStd_id());
        room.ifPresent(value -> roomService.addStudentToRoom(value.getRoomId(), (Student) student));
        return ResponseEntity.ok("added successfully");
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @DeleteMapping("/removeStudent")
    public ResponseEntity<?> removeStudentToRoom(@RequestBody StdToRoomDto stdToRoomDto) {
        Optional<Room> room = roomService.getRoom(stdToRoomDto.getRoom_id());
        if (room.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("room not found");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not authorized user");
        }

        Staff staff = (Staff) user;
        if (staff.getHostel() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("hostel not exists");
        }

        if (!staff.getHostel().equals(room.get().getHostel())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("hostel is not yours");
        }

        User student = userService.findById(stdToRoomDto.getStd_id());
        roomService.removeStudentFromRoom(student.getId());
        return ResponseEntity.ok("removed successfully");
    }

}