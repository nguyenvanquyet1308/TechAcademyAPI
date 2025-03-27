package com.academy.techacademyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.Student;
import com.academy.techacademyapi.entity.Student.StudentStatus;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByEmail(String email);
    
    List<Student> findByCourse(Course course);
    
    List<Student> findByStatus(StudentStatus status);
    
    List<Student> findByNameContainingIgnoreCase(String name);
} 