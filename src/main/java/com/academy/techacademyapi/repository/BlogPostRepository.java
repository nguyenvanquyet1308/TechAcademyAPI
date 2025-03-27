package com.academy.techacademyapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.techacademyapi.entity.BlogPost;
import com.academy.techacademyapi.entity.Instructor;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    
    Optional<BlogPost> findBySlug(String slug);
    
    List<BlogPost> findByPublished(Boolean published);
    
    List<BlogPost> findByTitleContainingIgnoreCase(String title);
    
    List<BlogPost> findByAuthor(Instructor author);
} 