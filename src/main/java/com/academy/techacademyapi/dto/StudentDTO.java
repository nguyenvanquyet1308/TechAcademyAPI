package com.academy.techacademyapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.academy.techacademyapi.entity.Student.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
    private Long courseId;
    private String courseName;
    private StudentStatus status;
    private LocalDate enrollmentDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 