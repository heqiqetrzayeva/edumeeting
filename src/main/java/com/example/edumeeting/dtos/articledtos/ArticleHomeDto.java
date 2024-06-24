package com.example.edumeeting.dtos.articledtos;

import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArticleHomeDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private Date Updated;
    private Date Created;
    private int views;
    private String seoUrl;
    private CategoryDto category;

}
