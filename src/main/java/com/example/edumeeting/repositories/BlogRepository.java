package com.example.edumeeting.repositories;


import com.example.edumeeting.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
//    List<Article> findByTitleContainingIgnoreCase(String title);


}
