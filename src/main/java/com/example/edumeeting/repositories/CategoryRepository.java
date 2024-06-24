package com.example.edumeeting.repositories;


import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1%")
    List<CategoryDto> findAll(String keyword);
}
