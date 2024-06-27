package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.categorydtos.CategoryCreateDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.models.Article;
import com.example.edumeeting.models.Category;
import com.example.edumeeting.repositories.CategoryRepository;
import com.example.edumeeting.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        category.setName(categoryCreateDto.getName());
        category.setImage(categoryCreateDto.getImage());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
//        if (keyword == null || keyword.isEmpty()) {
//            return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
//        }
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categories;
    }

    @Override
    public List<CategoryDto> searchCategories(String keyword) {
        List<Category> categories = categoryRepository.findByName(keyword.toLowerCase());
        List<CategoryDto> categoryDtos = categories.stream().
                map(Category->modelMapper.map(Category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }


    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id).map(category -> modelMapper.map(category, CategoryDto.class)).orElse(null);
    }


    



}
