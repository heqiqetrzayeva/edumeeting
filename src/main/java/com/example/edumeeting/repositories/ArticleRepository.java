package com.example.edumeeting.repositories;


import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<ArticleDto> searchByTitle(@Param("title") String title);

    @Query("SELECT a FROM Article a WHERE a.title=?1")
    List<Article> findByTitle(String title);


}
