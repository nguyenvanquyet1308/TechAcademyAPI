package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.StudentDTO;
import com.academy.techacademyapi.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    StudentDTO toDTO(Student student);
    
    List<StudentDTO> toDTOList(List<Student> students);
    
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Student toEntity(StudentDTO studentDTO);
    
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(StudentDTO studentDTO, @MappingTarget Student student);
} 