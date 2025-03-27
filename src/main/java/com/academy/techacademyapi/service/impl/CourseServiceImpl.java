package com.academy.techacademyapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.techacademyapi.dto.CourseDTO;
import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.Instructor;
import com.academy.techacademyapi.exception.ResourceNotFoundException;
import com.academy.techacademyapi.mapper.CourseMapper;
import com.academy.techacademyapi.repository.CourseRepository;
import com.academy.techacademyapi.repository.InstructorRepository;
import com.academy.techacademyapi.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toDTO(course);
    }
    
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        
        if (courseDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructorId()));
            course.setInstructor(instructor);
        }
        
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }
    
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        
        courseMapper.updateEntityFromDTO(courseDTO, existingCourse);
        
        if (courseDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructorId()));
            existingCourse.setInstructor(instructor);
        }
        
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDTO(updatedCourse);
    }
    
    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));
        
        List<Course> courses = courseRepository.findByInstructor(instructor);
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByActive(Boolean isActive) {
        List<Course> courses = courseRepository.findByIsActive(isActive);
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> searchCourses(String keyword) {
        List<Course> courses = courseRepository.findByNameContainingIgnoreCase(keyword);
        return courseMapper.toDTOList(courses);
    }
} 