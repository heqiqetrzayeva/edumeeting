package com.example.edumeeting.dtos.commentdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateDto {
    private Long id;
    private String comment;
    private Long articleId;
    private Long userId;
}
