package com.academy.techacademyapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.techacademyapi.dto.BlogPostDTO;
import com.academy.techacademyapi.service.BlogPostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/blog-posts")
@RequiredArgsConstructor
public class BlogPostController {
    
    private final BlogPostService blogPostService;
    
    @GetMapping
    public ResponseEntity<List<BlogPostDTO>> getAllBlogPosts(
            @RequestParam(required = false) Boolean published,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String search) {
        
        if (published != null && published) {
            return ResponseEntity.ok(blogPostService.getPublishedBlogPosts());
        } else if (authorId != null) {
            return ResponseEntity.ok(blogPostService.getBlogPostsByAuthorId(authorId));
        } else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(blogPostService.searchBlogPosts(search));
        } else {
            return ResponseEntity.ok(blogPostService.getAllBlogPosts());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BlogPostDTO> getBlogPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.getBlogPostById(id));
    }
    
    @GetMapping("/slug/{slug}")
    public ResponseEntity<BlogPostDTO> getBlogPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(blogPostService.getBlogPostBySlug(slug));
    }
    
    @PostMapping
    public ResponseEntity<BlogPostDTO> createBlogPost(@RequestBody BlogPostDTO blogPostDTO) {
        BlogPostDTO createdBlogPost = blogPostService.createBlogPost(blogPostDTO);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BlogPostDTO> updateBlogPost(@PathVariable Long id, @RequestBody BlogPostDTO blogPostDTO) {
        return ResponseEntity.ok(blogPostService.updateBlogPost(id, blogPostDTO));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return ResponseEntity.noContent().build();
    }
} 