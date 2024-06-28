package com.example.edumeeting.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment;


    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies;


    @ManyToOne
    private UserEntity user;


    @ManyToOne
    private Article article;

}
