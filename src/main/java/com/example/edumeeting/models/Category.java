package com.example.edumeeting.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;

    @OneToMany
    @JoinColumn(name="articles", nullable=true)
    private List<Article> articles;

    @OneToMany
    private List<Contact> contacts;
}
