package com.spark.blog.blog.dto.res;

import com.spark.blog.blog.entity.BlogStatus;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {

    private Long id;
    private String title;
    private String content;
    private BlogStatus status ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
