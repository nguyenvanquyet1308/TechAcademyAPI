package com.academy.techacademyapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.techacademyapi.dto.CourseChapterDTO;
import com.academy.techacademyapi.dto.CourseDTO;
import com.academy.techacademyapi.dto.CourseLessonDTO;
import com.academy.techacademyapi.entity.Course;
import com.academy.techacademyapi.entity.CourseChapter;
import com.academy.techacademyapi.entity.CourseLesson;
import com.academy.techacademyapi.entity.Instructor;
import com.academy.techacademyapi.exception.ResourceNotFoundException;
import com.academy.techacademyapi.mapper.CourseChapterMapper;
import com.academy.techacademyapi.mapper.CourseMapper;
import com.academy.techacademyapi.mapper.CourseLessonMapper;
import com.academy.techacademyapi.repository.CourseChapterRepository;
import com.academy.techacademyapi.repository.CourseLessonRepository;
import com.academy.techacademyapi.repository.CourseRepository;
import com.academy.techacademyapi.repository.InstructorRepository;
import com.academy.techacademyapi.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseChapterRepository chapterRepository;
    private final CourseLessonRepository lessonRepository;
    private final CourseMapper courseMapper;
    private final CourseChapterMapper chapterMapper;
    private final CourseLessonMapper lessonMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toDTO(course);
    }
    
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        
        if (courseDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructorId()));
            course.setInstructor(instructor);
        }
        
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDTO(savedCourse);
    }
    
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        
        courseMapper.updateEntityFromDTO(courseDTO, existingCourse);
        
        if (courseDTO.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(courseDTO.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + courseDTO.getInstructorId()));
            existingCourse.setInstructor(instructor);
        }
        
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toDTO(updatedCourse);
    }
    
    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + instructorId));
        
        List<Course> courses = courseRepository.findByInstructor(instructor);
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByActive(Boolean isActive) {
        List<Course> courses = courseRepository.findByIsActive(isActive);
        return courseMapper.toDTOList(courses);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> searchCourses(String keyword) {
        List<Course> courses = courseRepository.findByNameContainingIgnoreCase(keyword);
        return courseMapper.toDTOList(courses);
    }
    
    // Curriculum related methods implementation
    
    @Override
    public CourseChapterDTO addChapterToCourse(Long courseId, CourseChapterDTO chapterDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        CourseChapter chapter = chapterMapper.toEntity(chapterDTO);
        chapter.setCourse(course);
        
        // Find the highest position and add 1 to it
        Integer position = course.getChapters().stream()
                .map(CourseChapter::getPosition)
                .filter(p -> p != null)
                .max(Integer::compareTo)
                .orElse(0) + 1;
        
        chapter.setPosition(position);
        
        CourseChapter savedChapter = chapterRepository.save(chapter);
        return chapterMapper.toDTO(savedChapter);
    }
    
    @Override
    public CourseChapterDTO updateChapter(Long chapterId, CourseChapterDTO chapterDTO) {
        CourseChapter existingChapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ResourceNotFoundException("Chapter not found with id: " + chapterId));
        
        chapterMapper.updateEntityFromDTO(chapterDTO, existingChapter);
        
        CourseChapter updatedChapter = chapterRepository.save(existingChapter);
        return chapterMapper.toDTO(updatedChapter);
    }
    
    @Override
    public void deleteChapter(Long chapterId) {
        if (!chapterRepository.existsById(chapterId)) {
            throw new ResourceNotFoundException("Chapter not found with id: " + chapterId);
        }
        chapterRepository.deleteById(chapterId);
    }
    
    @Override
    public CourseLessonDTO addLessonToChapter(Long chapterId, CourseLessonDTO lessonDTO) {
        CourseChapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ResourceNotFoundException("Chapter not found with id: " + chapterId));
        
        CourseLesson lesson = lessonMapper.toEntity(lessonDTO);
        lesson.setChapter(chapter);
        
        // Find the highest position and add 1 to it
        Integer position = chapter.getLessons().stream()
                .map(CourseLesson::getPosition)
                .filter(p -> p != null)
                .max(Integer::compareTo)
                .orElse(0) + 1;
        
        lesson.setPosition(position);
        
        CourseLesson savedLesson = lessonRepository.save(lesson);
        
        // Recalculate the total course duration
        updateCourseDuration(chapter.getCourse());
        
        return lessonMapper.toDTO(savedLesson);
    }
    
    @Override
    public CourseLessonDTO updateLesson(Long lessonId, CourseLessonDTO lessonDTO) {
        CourseLesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        
        Integer originalDuration = existingLesson.getDuration();
        
        lessonMapper.updateEntityFromDTO(lessonDTO, existingLesson);
        
        CourseLesson updatedLesson = lessonRepository.save(existingLesson);
        
        // If duration changed, update the course total duration
        if (originalDuration == null || !originalDuration.equals(updatedLesson.getDuration())) {
            updateCourseDuration(updatedLesson.getChapter().getCourse());
        }
        
        return lessonMapper.toDTO(updatedLesson);
    }
    
    @Override
    public void deleteLesson(Long lessonId) {
        CourseLesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        
        Course course = lesson.getChapter().getCourse();
        
        lessonRepository.deleteById(lessonId);
        
        // Update course duration after deletion
        updateCourseDuration(course);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseChapterDTO> getCourseChapters(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        
        List<CourseChapter> chapters = chapterRepository.findByCourseOrderByPositionAsc(course);
        return chapterMapper.toDTOList(chapters);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CourseLessonDTO> getChapterLessons(Long chapterId) {
        CourseChapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ResourceNotFoundException("Chapter not found with id: " + chapterId));
        
        List<CourseLesson> lessons = lessonRepository.findByChapterOrderByPositionAsc(chapter);
        return lessonMapper.toDTOList(lessons);
    }
    
    /**
     * Recalculates and updates the total duration of a course based on lesson durations
     */
    private void updateCourseDuration(Course course) {
        Integer totalDuration = course.getChapters().stream()
                .flatMap(chapter -> chapter.getLessons().stream())
                .map(CourseLesson::getDuration)
                .filter(duration -> duration != null)
                .reduce(0, Integer::sum);
        
        course.setDuration(totalDuration);
        courseRepository.save(course);
    }
} 