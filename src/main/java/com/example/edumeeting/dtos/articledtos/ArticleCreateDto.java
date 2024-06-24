package com.example.edumeeting.dtos.articledtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateDto {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private Long categoryId;

}
