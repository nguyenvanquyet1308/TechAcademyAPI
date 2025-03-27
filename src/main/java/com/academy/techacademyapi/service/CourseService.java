package com.academy.techacademyapi.service;

import java.util.List;

import com.academy.techacademyapi.dto.CourseChapterDTO;
import com.academy.techacademyapi.dto.CourseDTO;
import com.academy.techacademyapi.dto.CourseLessonDTO;

public interface CourseService {
    
    List<CourseDTO> getAllCourses();
    
    CourseDTO getCourseById(Long id);
    
    CourseDTO createCourse(CourseDTO courseDTO);
    
    CourseDTO updateCourse(Long id, CourseDTO courseDTO);
    
    void deleteCourse(Long id);
    
    List<CourseDTO> getCoursesByInstructorId(Long instructorId);
    
    List<CourseDTO> getCoursesByActive(Boolean isActive);
    
    List<CourseDTO> searchCourses(String keyword);
    
    // Curriculum related methods
    CourseChapterDTO addChapterToCourse(Long courseId, CourseChapterDTO chapterDTO);
    
    CourseChapterDTO updateChapter(Long chapterId, CourseChapterDTO chapterDTO);
    
    void deleteChapter(Long chapterId);
    
    CourseLessonDTO addLessonToChapter(Long chapterId, CourseLessonDTO lessonDTO);
    
    CourseLessonDTO updateLesson(Long lessonId, CourseLessonDTO lessonDTO);
    
    void deleteLesson(Long lessonId);
    
    List<CourseChapterDTO> getCourseChapters(Long courseId);
    
    List<CourseLessonDTO> getChapterLessons(Long chapterId);
} 