package com.example.hostelManagement.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomStuDto {

    @NotNull(message = "roomId must not be null")
    private Integer studentId;

    @NotBlank(message = "studentName must not be blank")
    private String studentName;

    public RoomStuDto(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public @NotNull(message = "roomId must not be null") Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(@NotNull(message = "roomId must not be null") Integer studentId) {
        this.studentId = studentId;
    }

    public @NotBlank(message = "studentName must not be blank") String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NotBlank(message = "studentName must not be blank") String studentName) {
        this.studentName = studentName;
    }
}