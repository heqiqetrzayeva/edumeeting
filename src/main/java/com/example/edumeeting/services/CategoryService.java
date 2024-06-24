package com.example.edumeeting.services;


import com.example.edumeeting.dtos.categorydtos.CategoryCreateDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;

import java.util.List;

public interface CategoryService {

     void add(CategoryCreateDto categoryCreateDto);

     List<CategoryDto> getAllCategories();


}
