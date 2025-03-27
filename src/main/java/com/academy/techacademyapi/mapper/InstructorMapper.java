package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.InstructorDTO;
import com.academy.techacademyapi.entity.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    
    InstructorMapper INSTANCE = Mappers.getMapper(InstructorMapper.class);
    
    @Mapping(expression = "java(instructor.getCourses() != null ? instructor.getCourses().size() : 0)", target = "coursesCount")
    @Mapping(expression = "java(calculateStudentsCount(instructor))", target = "studentsCount")
    InstructorDTO toDTO(Instructor instructor);
    
    List<InstructorDTO> toDTOList(List<Instructor> instructors);
    
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Instructor toEntity(InstructorDTO instructorDTO);
    
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(InstructorDTO instructorDTO, @MappingTarget Instructor instructor);
    
    default Integer calculateStudentsCount(Instructor instructor) {
        if (instructor.getCourses() == null) {
            return 0;
        }
        return instructor.getCourses().stream()
            .mapToInt(course -> course.getStudents() != null ? course.getStudents().size() : 0)
            .sum();
    }
} 