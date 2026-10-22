package com.spark.blog.blog.dto.req;

import com.spark.blog.blog.entity.BlogStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BlogCreateRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private BlogStatus blogStatus = BlogStatus.PENDING;
}
