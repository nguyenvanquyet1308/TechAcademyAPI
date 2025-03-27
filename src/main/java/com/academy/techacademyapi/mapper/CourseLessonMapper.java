package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.CourseLessonDTO;
import com.academy.techacademyapi.entity.CourseLesson;

@Mapper(componentModel = "spring")
public interface CourseLessonMapper {
    
    CourseLessonMapper INSTANCE = Mappers.getMapper(CourseLessonMapper.class);
    
    @Mapping(source = "chapter.id", target = "chapterId")
    CourseLessonDTO toDTO(CourseLesson lesson);
    
    List<CourseLessonDTO> toDTOList(List<CourseLesson> lessons);
    
    @Mapping(target = "chapter", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CourseLesson toEntity(CourseLessonDTO lessonDTO);
    
    @Mapping(target = "chapter", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(CourseLessonDTO lessonDTO, @MappingTarget CourseLesson lesson);
} 