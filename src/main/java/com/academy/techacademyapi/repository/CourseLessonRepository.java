package com.academy.techacademyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.CourseChapter;
import com.academy.techacademyapi.entity.CourseLesson;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
    
    List<CourseLesson> findByChapterOrderByPositionAsc(CourseChapter chapter);
} 