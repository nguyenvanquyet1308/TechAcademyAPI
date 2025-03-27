package com.academy.techacademyapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.academy.techacademyapi.dto.StudentDTO;
import com.academy.techacademyapi.entity.Student.StudentStatus;
import com.academy.techacademyapi.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
@RequiredArgsConstructor
public class StudentController {
    
    private final StudentService studentService;
    
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {
        
        if (courseId != null) {
            return ResponseEntity.ok(studentService.getStudentsByCourseId(courseId));
        } else if (status != null && !status.isEmpty()) {
            try {
                StudentStatus studentStatus = StudentStatus.valueOf(status.toUpperCase());
                return ResponseEntity.ok(studentService.getStudentsByStatus(studentStatus));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        } else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(studentService.searchStudents(search));
        } else {
            return ResponseEntity.ok(studentService.getAllStudents());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
} 