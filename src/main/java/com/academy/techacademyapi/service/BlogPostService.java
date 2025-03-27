package com.academy.techacademyapi.service;

import java.util.List;

import com.academy.techacademyapi.dto.BlogPostDTO;

public interface BlogPostService {
    
    List<BlogPostDTO> getAllBlogPosts();
    
    BlogPostDTO getBlogPostById(Long id);
    
    BlogPostDTO getBlogPostBySlug(String slug);
    
    BlogPostDTO createBlogPost(BlogPostDTO blogPostDTO);
    
    BlogPostDTO updateBlogPost(Long id, BlogPostDTO blogPostDTO);
    
    void deleteBlogPost(Long id);
    
    List<BlogPostDTO> getPublishedBlogPosts();
    
    List<BlogPostDTO> getBlogPostsByAuthorId(Long authorId);
    
    List<BlogPostDTO> searchBlogPosts(String keyword);
} 