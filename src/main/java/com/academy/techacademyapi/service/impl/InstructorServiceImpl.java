package com.academy.techacademyapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.techacademyapi.dto.InstructorDTO;
import com.academy.techacademyapi.entity.Instructor;
import com.academy.techacademyapi.exception.ResourceNotFoundException;
import com.academy.techacademyapi.mapper.InstructorMapper;
import com.academy.techacademyapi.repository.InstructorRepository;
import com.academy.techacademyapi.service.InstructorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {
    
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<InstructorDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructorMapper.toDTOList(instructors);
    }
    
    @Override
    @Transactional(readOnly = true)
    public InstructorDTO getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
        return instructorMapper.toDTO(instructor);
    }
    
    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return instructorMapper.toDTO(savedInstructor);
    }
    
    @Override
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + id));
        
        instructorMapper.updateEntityFromDTO(instructorDTO, existingInstructor);
        
        Instructor updatedInstructor = instructorRepository.save(existingInstructor);
        return instructorMapper.toDTO(updatedInstructor);
    }
    
    @Override
    public void deleteInstructor(Long id) {
        if (!instructorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Instructor not found with id: " + id);
        }
        instructorRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<InstructorDTO> searchInstructors(String keyword) {
        List<Instructor> instructors = instructorRepository.findByNameContainingIgnoreCase(keyword);
        return instructorMapper.toDTOList(instructors);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<InstructorDTO> getInstructorsBySpecialty(String specialty) {
        List<Instructor> instructors = instructorRepository.findBySpecialtyIgnoreCase(specialty);
        return instructorMapper.toDTOList(instructors);
    }
} 