package com.example.edumeeting.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    private String subTitle;
    @Column(name= "description", length=10000)
    private String description;
    @Column(length=1000)
    private String image;
    private Date Updated;
    private Date Created;
    private int views;
    private Boolean isDeleted;

}
