package com.academy.techacademyapi.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseChapterDTO {
    
    private Long id;
    private String title;
    private Integer position;
    private Long courseId;
    private List<CourseLessonDTO> lessons = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 