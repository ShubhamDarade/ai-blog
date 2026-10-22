package com.spark.blog.blog.service;

import com.spark.blog.blog.dto.req.BlogCreateRequest;
import com.spark.blog.blog.dto.res.BlogResponse;
import com.spark.blog.blog.entity.Blog;

import java.util.List;

public interface BlogService {
    BlogResponse createBlog(BlogCreateRequest blog);
    List<BlogResponse> getAllBlogs();
}
