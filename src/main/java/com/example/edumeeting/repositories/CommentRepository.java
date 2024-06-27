package com.example.edumeeting.repositories;

import com.example.edumeeting.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(long articleId);

}
