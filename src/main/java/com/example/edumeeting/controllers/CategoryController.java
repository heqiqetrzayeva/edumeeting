package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.categorydtos.CategoryCreateDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/admin/category/create")
    public String categoryCreate() {
        return "/admin/category/create";
    }


    @GetMapping("/admin/category")
    public String categoryGet(Model model, @Param("keyword") String keyword) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/admin/category/category";
    }


    @PostMapping("/admin/category/create")
    public String categoryCreate(@ModelAttribute CategoryCreateDto categoryCreateDto) {
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category/category";
    }

}
