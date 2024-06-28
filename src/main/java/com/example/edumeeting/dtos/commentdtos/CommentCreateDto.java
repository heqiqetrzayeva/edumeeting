package com.example.edumeeting.dtos.commentdtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    private Long articleId;
    private String content;
}
