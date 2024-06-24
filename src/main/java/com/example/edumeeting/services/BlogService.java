package com.example.edumeeting.services;


import com.example.edumeeting.dtos.blogdtos.BlogCreateDto;
import com.example.edumeeting.dtos.blogdtos.BlogDto;

import java.util.List;

public interface BlogService {

     List<BlogDto> getAllBlogs();

     void addBlog(BlogCreateDto blogCreateDto);

     BlogDto getBlogById(Long id);













}
