package com.example.edumeeting.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String message;
    @Column(length=10000)
    private String resumePath;

//    @Lob
//    @Column(nullable = false)
//    private byte[] data;

    @ManyToOne
    private Category category;


    @ManyToOne
    private Vacancy vacancy;
}
