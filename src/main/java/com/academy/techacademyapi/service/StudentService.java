package com.academy.techacademyapi.service;

import java.util.List;

import com.academy.techacademyapi.dto.StudentDTO;
import com.academy.techacademyapi.entity.Student.StudentStatus;

public interface StudentService {
    
    List<StudentDTO> getAllStudents();
    
    StudentDTO getStudentById(Long id);
    
    StudentDTO createStudent(StudentDTO studentDTO);
    
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    
    void deleteStudent(Long id);
    
    List<StudentDTO> getStudentsByCourseId(Long courseId);
    
    List<StudentDTO> getStudentsByStatus(StudentStatus status);
    
    List<StudentDTO> searchStudents(String keyword);
} 