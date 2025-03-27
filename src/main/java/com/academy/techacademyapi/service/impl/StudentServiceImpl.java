package com.academy.techacademyapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.techacademyapi.dto.StudentDTO;
import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.Student;
import com.academy.techacademyapi.entity.Student.StudentStatus;
import com.academy.techacademyapi.exception.ResourceNotFoundException;
import com.academy.techacademyapi.mapper.StudentMapper;
import com.academy.techacademyapi.repository.CourseRepository;
import com.academy.techacademyapi.repository.StudentRepository;
import com.academy.techacademyapi.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDTOList(students);
    }
    
    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return studentMapper.toDTO(student);
    }
    
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        
        if (studentDTO.getCourseId() != null) {
            Course course = courseRepository.findById(studentDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + studentDTO.getCourseId()));
            student.setCourse(course);
        }
        
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);
    }
    
    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        
        studentMapper.updateEntityFromDTO(studentDTO, existingStudent);
        
        if (studentDTO.getCourseId() != null) {
            Course course = courseRepository.findById(studentDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + studentDTO.getCourseId()));
            existingStudent.setCourse(course);
        }
        
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toDTO(updatedStudent);
    }
    
    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        List<Student> students = studentRepository.findByCourse(course);
        return studentMapper.toDTOList(students);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByStatus(StudentStatus status) {
        List<Student> students = studentRepository.findByStatus(status);
        return studentMapper.toDTOList(students);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> searchStudents(String keyword) {
        List<Student> students = studentRepository.findByNameContainingIgnoreCase(keyword);
        return studentMapper.toDTOList(students);
    }
} 