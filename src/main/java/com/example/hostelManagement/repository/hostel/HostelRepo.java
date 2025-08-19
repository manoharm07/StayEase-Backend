package com.example.hostelManagement.repository.hostel;

import com.example.hostelManagement.models.hostel.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelRepo extends JpaRepository<Hostel,Integer> {

    @Query("SELECT h FROM Hostel h WHERE LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Hostel> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT h FROM Hostel h WHERE LOWER(h.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Hostel> findByLocationContainingIgnoreCase(@Param("location") String location);

    @Query("SELECT h FROM Hostel h WHERE h.fees < :fees")
    List<Hostel> findByFeesLessThan(@Param("fees") Integer fees);
}