package com.example.edumeeting.dtos.blogdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogCreateDto {
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private Long categoryId;

}
