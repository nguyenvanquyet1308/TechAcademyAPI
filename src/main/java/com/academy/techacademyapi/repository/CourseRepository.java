package com.academy.techacademyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.Instructor;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByInstructor(Instructor instructor);
    
    List<Course> findByIsActive(Boolean isActive);
    
    List<Course> findByNameContainingIgnoreCase(String name);
    
    List<Course> findByLevelIgnoreCase(String level);
    
} 