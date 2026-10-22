package com.spark.blog.blog.controller;

import com.spark.blog.blog.constants.ApiEndpoints;
import com.spark.blog.blog.dto.req.BlogCreateRequest;
import com.spark.blog.blog.dto.res.BlogResponse;
import com.spark.blog.blog.service.BlogService;
import com.spark.falcon.model.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiEndpoints.BLOG_BASE)
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BlogResponse>> createBlog(@Valid @RequestBody BlogCreateRequest blog) {
        log.info("create blog started");
        BlogResponse newBlog = blogService.createBlog(blog);
        ApiResponse<BlogResponse> response = ApiResponse.success("Blog created successfully", newBlog);
        log.info("create blog ended");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BlogResponse>>> getAllBlogs() {
        List<BlogResponse> blogs = blogService.getAllBlogs();
        ApiResponse<List<BlogResponse>> response = ApiResponse.success("Fetch all blogs", blogs);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(ApiEndpoints.BLOG_ID)
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.noContent().build();
    }
}
