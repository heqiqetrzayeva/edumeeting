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
import com.example.edumeeting.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Autowired
    private UserService userService;

    @Override
    public void addComment(CommentCreateDto commentCreate, String email) {
        UserEntity user = userService.findByEmail(email);
        Comment newComment = modelMapper.map(commentCreate, Comment.class);
        newComment.setUser(user);
        newComment.setCreatedDate(new Date());
        commentRepository.save(newComment);
    }

    public Comment addReply(Long parentId, Comment reply) {
        Comment parentComment = commentRepository.findById(parentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        reply.setParentComment(parentComment);
        return commentRepository.save(reply);
    }

    @Override
    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        List<Comment> result = commentRepository.findByArticleId(articleId);
        List<CommentDto> comments = result.stream().map(c ->{
            CommentDto comment = modelMapper.map(c, CommentDto.class);
            comment.setFullName(c.getUser().getFirstName() + " " + c.getUser().getLastName());
            return comment;
        }).collect(Collectors.toList());

        return comments;
    }
//
//
//    @Override
//    public void addComment(CommentCreateDto commentCreateDto, String username) {
//
//        Comment comment = new Comment();
//        UserEntity user = userRepository.findById(commentCreateDto.getUserId()).orElseThrow();
//
//        comment.setComment(commentCreateDto.getComment());
//        comment.setUser(user);
//        comment.setCreatedDate(LocalDateTime.now());
//
//        commentRepository.save(comment);
//    }
}
