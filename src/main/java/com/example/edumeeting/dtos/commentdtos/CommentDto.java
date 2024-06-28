package com.example.edumeeting.dtos.commentdtos;

import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long articleId;
    private String content;
    private String fullName;
    private LocalDateTime createdDate;
}
