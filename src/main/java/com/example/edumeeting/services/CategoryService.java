package com.example.edumeeting.services;


import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.categorydtos.CategoryCreateDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;

import java.util.List;

public interface CategoryService {

     void add(CategoryCreateDto categoryCreateDto);

     List<CategoryDto> getAllCategories();

     List<CategoryDto> searchCategories(String keyword);

     void removeCategory(Long categoryId);



}
