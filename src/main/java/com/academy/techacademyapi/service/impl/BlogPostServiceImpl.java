package com.academy.techacademyapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academy.techacademyapi.dto.BlogPostDTO;
import com.academy.techacademyapi.entity.BlogPost;
import com.academy.techacademyapi.entity.Instructor;
import com.academy.techacademyapi.exception.ResourceNotFoundException;
import com.academy.techacademyapi.mapper.BlogPostMapper;
import com.academy.techacademyapi.repository.BlogPostRepository;
import com.academy.techacademyapi.repository.InstructorRepository;
import com.academy.techacademyapi.service.BlogPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogPostServiceImpl implements BlogPostService {
    
    private final BlogPostRepository blogPostRepository;
    private final InstructorRepository instructorRepository;
    private final BlogPostMapper blogPostMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<BlogPostDTO> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();
        return blogPostMapper.toDTOList(blogPosts);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BlogPostDTO getBlogPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with id: " + id));
        return blogPostMapper.toDTO(blogPost);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BlogPostDTO getBlogPostBySlug(String slug) {
        BlogPost blogPost = blogPostRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with slug: " + slug));
        return blogPostMapper.toDTO(blogPost);
    }
    
    @Override
    public BlogPostDTO createBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostMapper.toEntity(blogPostDTO);
        
        if (blogPostDTO.getAuthorId() != null) {
            Instructor author = instructorRepository.findById(blogPostDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + blogPostDTO.getAuthorId()));
            blogPost.setAuthor(author);
        }
        
        // Set published time if published is true
        if (Boolean.TRUE.equals(blogPost.getPublished()) && blogPost.getPublishedAt() == null) {
            blogPost.setPublishedAt(LocalDateTime.now());
        }
        
        BlogPost savedBlogPost = blogPostRepository.save(blogPost);
        return blogPostMapper.toDTO(savedBlogPost);
    }
    
    @Override
    public BlogPostDTO updateBlogPost(Long id, BlogPostDTO blogPostDTO) {
        BlogPost existingBlogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog post not found with id: " + id));
        
        // Get the current publish state before updating
        boolean wasPublished = Boolean.TRUE.equals(existingBlogPost.getPublished());
        
        blogPostMapper.updateEntityFromDTO(blogPostDTO, existingBlogPost);
        
        if (blogPostDTO.getAuthorId() != null) {
            Instructor author = instructorRepository.findById(blogPostDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + blogPostDTO.getAuthorId()));
            existingBlogPost.setAuthor(author);
        }
        
        // If published for the first time, set published date
        if (!wasPublished && Boolean.TRUE.equals(existingBlogPost.getPublished()) && existingBlogPost.getPublishedAt() == null) {
            existingBlogPost.setPublishedAt(LocalDateTime.now());
        }
        
        BlogPost updatedBlogPost = blogPostRepository.save(existingBlogPost);
        return blogPostMapper.toDTO(updatedBlogPost);
    }
    
    @Override
    public void deleteBlogPost(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog post not found with id: " + id);
        }
        blogPostRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BlogPostDTO> getPublishedBlogPosts() {
        List<BlogPost> publishedPosts = blogPostRepository.findByPublished(true);
        return blogPostMapper.toDTOList(publishedPosts);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BlogPostDTO> getBlogPostsByAuthorId(Long authorId) {
        Instructor author = instructorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + authorId));
        
        List<BlogPost> authorPosts = blogPostRepository.findByAuthor(author);
        return blogPostMapper.toDTOList(authorPosts);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<BlogPostDTO> searchBlogPosts(String keyword) {
        List<BlogPost> searchResults = blogPostRepository.findByTitleContainingIgnoreCase(keyword);
        return blogPostMapper.toDTOList(searchResults);
    }
} 