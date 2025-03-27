package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.CourseDTO;
import com.academy.techacademyapi.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    
    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "instructor.name", target = "instructorName")
    @Mapping(expression = "java(course.getStudents() != null ? course.getStudents().size() : 0)", target = "studentsCount")
    CourseDTO toDTO(Course course);
    
    List<CourseDTO> toDTOList(List<Course> courses);
    
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Course toEntity(CourseDTO courseDTO);
    
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(CourseDTO courseDTO, @MappingTarget Course course);
} 