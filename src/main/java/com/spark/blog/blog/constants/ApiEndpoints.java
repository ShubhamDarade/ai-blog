package com.spark.blog.blog.constants;

public final class ApiEndpoints {
    private ApiEndpoints() {}

    public static final String BLOG_BASE = "/api/blogs";

    public static final String HEALTH = "/health";
    public static final String BLOG_ID = "/{blogId}";
    public static final String STUDENT_ID = "/{studentId}";
    public static final String LIKE_BLOG = "/{id}/like";
    public static final String GET_BY_TITLE = "/getByTitle";
}