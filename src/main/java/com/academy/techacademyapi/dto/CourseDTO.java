package com.academy.techacademyapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String thumbnail;
    private Integer duration;
    private String level;
    private Long instructorId;
    private String instructorName;
    private Integer studentsCount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 