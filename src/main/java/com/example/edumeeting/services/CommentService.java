package com.example.edumeeting.services;

import com.example.edumeeting.dtos.commentdtos.CommentCreateDto;
import com.example.edumeeting.dtos.commentdtos.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByArticleId(Long articleId);

    void addComment(CommentCreateDto createComment, String username);
}