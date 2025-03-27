package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.CourseChapterDTO;
import com.academy.techacademyapi.entity.CourseChapter;

@Mapper(componentModel = "spring", uses = {CourseLessonMapper.class})
public interface CourseChapterMapper {
    
    CourseChapterMapper INSTANCE = Mappers.getMapper(CourseChapterMapper.class);
    
    @Mapping(source = "course.id", target = "courseId")
    CourseChapterDTO toDTO(CourseChapter chapter);
    
    List<CourseChapterDTO> toDTOList(List<CourseChapter> chapters);
    
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CourseChapter toEntity(CourseChapterDTO chapterDTO);
    
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(CourseChapterDTO chapterDTO, @MappingTarget CourseChapter chapter);
} 