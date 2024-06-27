package com.example.edumeeting.services.impls;

import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.commentdtos.CommentCreateDto;
import com.example.edumeeting.dtos.commentdtos.CommentDto;
import com.example.edumeeting.models.Article;
import com.example.edumeeting.models.Category;
import com.example.edumeeting.models.Comment;
import com.example.edumeeting.models.UserEntity;
import com.example.edumeeting.repositories.ArticleRepository;
import com.example.edumeeting.repositories.CommentRepository;
import com.example.edumeeting.repositories.UserRepository;
import com.example.edumeeting.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addComment(CommentCreateDto commentCreateDto, String username) {

        Comment comment = new Comment();
        UserEntity user = userRepository.findById(commentCreateDto.getUserId()).orElseThrow();

        comment.setComment(commentCreateDto.getComment());
        comment.setUser(user);
        comment.setCreatedDate(LocalDateTime.now());

        commentRepository.save(comment);
    }
}
