package com.academy.techacademyapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.academy.techacademyapi.dto.InstructorDTO;
import com.academy.techacademyapi.service.InstructorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class InstructorController {
    
    private final InstructorService instructorService;
    
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String search) {
        
        if (specialty != null && !specialty.isEmpty()) {
            return ResponseEntity.ok(instructorService.getInstructorsBySpecialty(specialty));
        } else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(instructorService.searchInstructors(search));
        } else {
            return ResponseEntity.ok(instructorService.getAllInstructors());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }
    
    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructorDTO) {
        InstructorDTO createdInstructor = instructorService.createInstructor(instructorDTO);
        return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable Long id, @RequestBody InstructorDTO instructorDTO) {
        return ResponseEntity.ok(instructorService.updateInstructor(id, instructorDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
} 