package com.academy.techacademyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
    Optional<Instructor> findByEmail(String email);
    
    List<Instructor> findByNameContainingIgnoreCase(String name);
    
    List<Instructor> findBySpecialtyIgnoreCase(String specialty);
} 