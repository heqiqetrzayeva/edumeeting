package com.example.edumeeting.dtos.blogdtos;

import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BlogDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private Date Updated;
    private Date Created;
    private int views;
    private CategoryDto category;

}
