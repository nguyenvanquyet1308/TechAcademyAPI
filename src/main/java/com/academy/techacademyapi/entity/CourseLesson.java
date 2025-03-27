package com.academy.techacademyapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course_lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseLesson {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    private Integer duration; // Duration in minutes
    
    private Integer position;
    
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private CourseChapter chapter;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    private String videoUrl;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 