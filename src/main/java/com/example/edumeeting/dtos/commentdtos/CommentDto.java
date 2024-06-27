package com.example.edumeeting.dtos.commentdtos;

import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String comment;
    private String author;
    private LocalDateTime createdDate;
    private ArticleDto article;
    private UserDto user;

}
