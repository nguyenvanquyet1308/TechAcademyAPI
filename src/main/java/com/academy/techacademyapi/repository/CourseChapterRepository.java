package com.academy.techacademyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.CourseChapter;

@Repository
public interface CourseChapterRepository extends JpaRepository<CourseChapter, Long> {
    
    List<CourseChapter> findByCourseOrderByPositionAsc(Course course);
} 