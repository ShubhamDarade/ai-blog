package com.spark.blog.blog.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.spark.blog.blog.dto.req.BlogCreateRequest;
import com.spark.blog.blog.dto.res.BlogResponse;
import com.spark.blog.blog.entity.Blog;
import com.spark.blog.blog.exception.BlogNotFoundException;
import com.spark.blog.blog.repository.BlogRepository;
import com.spark.blog.blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogResponse createBlog(BlogCreateRequest blogReq) {
        log.info("Creating new blog");
        Blog blog = Blog.builder()
                .title(blogReq.getTitle())
                .content(blogReq.getContent())
                .blogStatus(blogReq.getBlogStatus())
                .build();

        Blog savedBlog = blogRepository.save(blog);
        return mapToResponse(savedBlog);
    }

    @Override
    public List<BlogResponse> getAllBlogs() {
        return blogRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException("Blog with id " + blogId + " not found"));
        blogRepository.delete(blog);
    }

    private BlogResponse mapToResponse(Blog blog) {
        BlogResponse dto = new BlogResponse();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setStatus(blog.getBlogStatus());
        dto.setCreatedAt(blog.getCreatedAt());
        dto.setUpdatedAt(blog.getUpdatedAt());
        return dto;
    }
}
