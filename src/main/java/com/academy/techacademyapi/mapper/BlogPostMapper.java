package com.academy.techacademyapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.academy.techacademyapi.dto.BlogPostDTO;
import com.academy.techacademyapi.entity.BlogPost;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    
    BlogPostMapper INSTANCE = Mappers.getMapper(BlogPostMapper.class);
    
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    BlogPostDTO toDTO(BlogPost blogPost);
    
    List<BlogPostDTO> toDTOList(List<BlogPost> blogPosts);
    
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    BlogPost toEntity(BlogPostDTO blogPostDTO);
    
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(BlogPostDTO blogPostDTO, @MappingTarget BlogPost blogPost);
} 