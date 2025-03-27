package com.academy.techacademyapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.techacademyapi.dto.CourseChapterDTO;
import com.academy.techacademyapi.dto.CourseDTO;
import com.academy.techacademyapi.dto.CourseLessonDTO;
import com.academy.techacademyapi.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    
    private final CourseService courseService;
    
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(
            @RequestParam(required = false) Boolean active,
            @RequestParam(required = false) String search) {
        
        if (active != null) {
            return ResponseEntity.ok(courseService.getCoursesByActive(active));
        } else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(courseService.searchCourses(search));
        } else {
            return ResponseEntity.ok(courseService.getAllCourses());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getCoursesByInstructorId(@PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.getCoursesByInstructorId(instructorId));
    }
    
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    // Curriculum management endpoints
    
    @GetMapping("/{courseId}/chapters")
    public ResponseEntity<List<CourseChapterDTO>> getCourseChapters(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourseChapters(courseId));
    }
    
    @PostMapping("/{courseId}/chapters")
    public ResponseEntity<CourseChapterDTO> addChapterToCourse(
            @PathVariable Long courseId, 
            @RequestBody CourseChapterDTO chapterDTO) {
        CourseChapterDTO createdChapter = courseService.addChapterToCourse(courseId, chapterDTO);
        return new ResponseEntity<>(createdChapter, HttpStatus.CREATED);
    }
    
    @PutMapping("/chapters/{chapterId}")
    public ResponseEntity<CourseChapterDTO> updateChapter(
            @PathVariable Long chapterId, 
            @RequestBody CourseChapterDTO chapterDTO) {
        return ResponseEntity.ok(courseService.updateChapter(chapterId, chapterDTO));
    }
    
    @DeleteMapping("/chapters/{chapterId}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long chapterId) {
        courseService.deleteChapter(chapterId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/chapters/{chapterId}/lessons")
    public ResponseEntity<List<CourseLessonDTO>> getChapterLessons(@PathVariable Long chapterId) {
        return ResponseEntity.ok(courseService.getChapterLessons(chapterId));
    }
    
    @PostMapping("/chapters/{chapterId}/lessons")
    public ResponseEntity<CourseLessonDTO> addLessonToChapter(
            @PathVariable Long chapterId, 
            @RequestBody CourseLessonDTO lessonDTO) {
        CourseLessonDTO createdLesson = courseService.addLessonToChapter(chapterId, lessonDTO);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }
    
    @PutMapping("/lessons/{lessonId}")
    public ResponseEntity<CourseLessonDTO> updateLesson(
            @PathVariable Long lessonId, 
            @RequestBody CourseLessonDTO lessonDTO) {
        return ResponseEntity.ok(courseService.updateLesson(lessonId, lessonDTO));
    }
    
    @DeleteMapping("/lessons/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
        courseService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
} 