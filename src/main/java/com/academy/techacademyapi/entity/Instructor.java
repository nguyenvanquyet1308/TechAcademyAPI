package com.academy.techacademyapi.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    private String phone;
    
    private String avatar;
    
    private String specialty;
    
    @ElementCollection
    @CollectionTable(name = "instructor_skills", joinColumns = @JoinColumn(name = "instructor_id"))
    @Column(name = "skill")
    private Set<String> skills = new HashSet<>();
    
    private String linkedin;
    
    private String github;
    
    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses = new HashSet<>();
    
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