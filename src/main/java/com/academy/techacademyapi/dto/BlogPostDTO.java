package com.academy.techacademyapi.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDTO {
    
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String thumbnail;
    private Set<String> tags = new HashSet<>();
    private Long authorId;
    private String authorName;
    private Boolean published;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 