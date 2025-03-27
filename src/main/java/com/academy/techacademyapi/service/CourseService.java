package com.academy.techacademyapi.service;

import java.util.List;

import com.academy.techacademyapi.dto.CourseDTO;

public interface CourseService {
    
    List<CourseDTO> getAllCourses();
    
    CourseDTO getCourseById(Long id);
    
    CourseDTO createCourse(CourseDTO courseDTO);
    
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    
    void deleteCourse(Long id);
    
    List<CourseDTO> getCoursesByInstructorId(Long instructorId);
    
    List<CourseDTO> getCoursesByActive(Boolean isActive);
    
    List<CourseDTO> searchCourses(String keyword);
} 