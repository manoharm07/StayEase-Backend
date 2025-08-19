package com.example.hostelManagement.repository;

import com.example.hostelManagement.models.Application;
import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.models.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Integer> {

    @Query("SELECT a FROM Application a WHERE a.hostel = :hostel")
    List<Application> findAllByHostel(@Param("hostel") Hostel hostel);

    @Query("SELECT a FROM Application a WHERE a.student = :student")
    List<Application> findAllByStudent(@Param("student") Student student);

    @Query("SELECT COUNT(a) > 0 FROM Application a WHERE a.student = :student AND a.hostel = :hostel")
    boolean existsByStudentAndHostel(@Param("student") Student student, @Param("hostel") Hostel hostel);
}
