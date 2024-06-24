package com.example.edumeeting.services;


import com.example.edumeeting.dtos.articledtos.ArticleCreateDto;
import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService{

     List<ArticleDto> getAllArticles();
     List<ArticleHomeDto> getHomeArticles();

     void addArticle(ArticleCreateDto articleCreateDto);

     ArticleDto getArticleById(Long id);

     List<ArticleDto> getTwoArticles();

     List<ArticleHomeDto> searchArticles(String keyword);

     Page<ArticleDto> getArticles(int page, int size);

     void removeArticle(Long articleId);
     void updateArticle(ArticleCreateDto articleCreateDto);
     ArticleCreateDto findUpdateArticle(Long id);

     ArticleHomeDto findHomeArticle(Long id);














     }
