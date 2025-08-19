package com.example.hostelManagement.repository.hostel;

import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.hostel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Integer> {

    @Query("SELECT r FROM Room r WHERE r.hostel = :hostel AND r.floorNo = :floorNo ORDER BY r.roomNo ASC")
    List<Room> findByHostelAndFloorNoOrderByRoomNoAsc(@Param("hostel") Hostel hostel, @Param("floorNo") Integer floorNo);
}