package com.spark.blog.blog.service.impl;

import com.spark.blog.blog.entity.Blog;
import com.spark.blog.blog.exception.BlogNotFoundException;
import com.spark.blog.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlogServiceImplTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogServiceImpl blogService;

    @Test
    void deleteBlog_deletesExistingBlog() {
        Blog blog = Blog.builder().id(7L).build();
        when(blogRepository.findById(7L)).thenReturn(Optional.of(blog));

        blogService.deleteBlog(7L);

        verify(blogRepository).delete(blog);
    }

    @Test
    void deleteBlog_throwsWhenBlogDoesNotExist() {
        when(blogRepository.findById(7L)).thenReturn(Optional.empty());

        assertThrows(BlogNotFoundException.class, () -> blogService.deleteBlog(7L));

        verify(blogRepository, never()).delete(org.mockito.ArgumentMatchers.any(Blog.class));
    }
}