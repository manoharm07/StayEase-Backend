package com.example.hostelManagement.service.hostel;


import com.example.hostelManagement.dto.RoomDto;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.hostel.Room;
import com.example.hostelManagement.models.user.Student;
import com.example.hostelManagement.repository.hostel.RoomRepo;
import com.example.hostelManagement.repository.user.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private StudentRepo studentRepo;

    public void createRoomsForFixedSize(int floorNo, int totalRooms, int startRoomNo, Hostel hostel, int totalSeatsInEachRoom) {
        int i = startRoomNo;
        while (i < startRoomNo + totalRooms) {
            Room room = new Room();
            room.setRoomNo(i);
            room.setHostel(hostel);
            room.setFloorNo(floorNo);
            room.setTotalSeats(totalSeatsInEachRoom);
            room.setEmptySeats(totalSeatsInEachRoom);
            roomRepo.save(room);
            i++;
        }
    }

    public void createRoom(int floorNo, int roomNo, Hostel hostel, int totalSeatsInRoom) {
        Room room = new Room();
        room.setRoomNo(roomNo);
        room.setHostel(hostel);
        room.setFloorNo(floorNo);
        room.setTotalSeats(totalSeatsInRoom);
        room.setEmptySeats(totalSeatsInRoom);
        roomRepo.save(room);
    }

    public Optional<Room> getRoom(int roomId) {
        return roomRepo.findById(roomId);
    }

    @Transactional
    public void addStudentToRoom(int roomId, Student student) {
        Optional<Room> roomOptional = roomRepo.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            student.setRoom(room);
            studentRepo.save(student);
        } else {
            throw new RuntimeException("Room not found!");
        }
    }


    @Transactional
    public void removeStudentFromRoom(Integer studentId) {
        Optional<Student> studentOptional = studentRepo.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setRoom(null);
            studentRepo.save(student);
        } else {
            throw new RuntimeException("Student not found!");
        }
    }

    @Transactional
    public void deleteRoom(Integer roomId) {
        Optional<Room> roomOptional = roomRepo.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            for (Student student : room.getStudents()) {
                student.setRoom(null);
                studentRepo.save(student);
            }
            roomRepo.delete(room);
        } else {
            throw new RuntimeException("Room not found!");
        }
    }

    public List<RoomDto> getRoomsByHostelAndFloor(Hostel hostel, Integer floorNo) {
        return roomRepo.findByHostelAndFloorNoOrderByRoomNoAsc(hostel, floorNo).stream().map(
                room -> new RoomDto(
                        room.getRoomId(),
                        room.getRoomNo(),
                        room.getFloorNo(),
                        room.getTotalSeats(),
                        room.getEmptySeats()
                )
        ).toList();
    }
}