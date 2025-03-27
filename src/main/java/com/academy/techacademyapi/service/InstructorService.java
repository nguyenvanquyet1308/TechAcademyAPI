package com.academy.techacademyapi.service;

import java.util.List;

import com.academy.techacademyapi.dto.InstructorDTO;

public interface InstructorService {
    
    List<InstructorDTO> getAllInstructors();
    
    InstructorDTO getInstructorById(Long id);
    
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    
    InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO);
    
    void deleteInstructor(Long id);
    
    List<InstructorDTO> searchInstructors(String keyword);
    
    List<InstructorDTO> getInstructorsBySpecialty(String specialty);
} 