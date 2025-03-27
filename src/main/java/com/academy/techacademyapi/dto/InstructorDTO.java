package com.academy.techacademyapi.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {
    
    private Long id;
    private String name;
    private String title;
    private String bio;
    private String email;
    private String phone;
    private String avatar;
    private String specialty;
    private Set<String> skills = new HashSet<>();
    private String linkedin;
    private String github;
    private Integer coursesCount;
    private Integer studentsCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 