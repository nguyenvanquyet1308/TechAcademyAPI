package com.academy.techacademyapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseLessonDTO {
    
    private Long id;
    private String title;
    private Integer duration;
    private Integer position;
    private Long chapterId;
    private String content;
    private String videoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 