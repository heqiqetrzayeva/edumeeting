package com.example.edumeeting.services.impls;


import com.example.edumeeting.dtos.articledtos.ArticleCreateDto;
import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.articledtos.ArticleRelatedDto;
import com.example.edumeeting.helpers.SeoHelper;
import com.example.edumeeting.models.Article;
import com.example.edumeeting.models.Category;
import com.example.edumeeting.repositories.ArticleRepository;
import com.example.edumeeting.repositories.CategoryRepository;
import com.example.edumeeting.services.ArticleService;
import com.example.edumeeting.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    @Autowired
//    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articles = articleRepository.findAll().stream()
                .filter(x -> x.getIsDeleted()==false) // Null kontrolü yaparak isDeleted false olanları filtrele
                .map(Article->modelMapper.map(Article, ArticleDto.class))
                .collect(Collectors.toList());
        return articles;
    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {
        List<ArticleHomeDto> articles = articleRepository.findAll().stream()
                .filter(x -> x.getIsDeleted()==false) // Null kontrolü yaparak isDeleted false olanları filtrele
                .map(Article->modelMapper.map(Article, ArticleHomeDto.class))
                .collect(Collectors.toList());
        return articles;
    }

//    @Override
//    public List<ArticleDto> getAllArticles(int pageNumber, String searchKey) {
//        Pageable pageable = (Pageable) PageRequest.of(pageNumber,12);
//
//        if (searchKey != null && !searchKey.isEmpty()) {
//            return articleRepository.findAll(pageable);
//        }
//        else{
//          return articleRepository.findByArticleNameContainingIgnore(
//                    searchKey,searchKey,pageable
//            );
//
//        }
//
//    }

    @Override
    public void addArticle(ArticleCreateDto articleCreateDto) {
        Article article = new Article();
        Category category = categoryRepository
                .findById(articleCreateDto.getCategoryId()).get();
        article.setTitle(articleCreateDto.getTitle());
        article.setSubTitle(articleCreateDto.getSubTitle());
        article.setImage(articleCreateDto.getImage());
        article.setDescription(articleCreateDto.getDescription());
        SeoHelper seoHelper = new SeoHelper();
        article.setSeoUrl(seoHelper.seoHelper(articleCreateDto.getTitle()));
        article.setUpdated(new Date());
        article.setCreated(new Date());
        article.setCategory(category);
        articleRepository.save(article);

    }

    @Override
    public List<ArticleDto> getTwoArticles() {
        List<ArticleDto> articles = articleRepository.findAll().stream()
                .filter(x -> x.getIsDeleted()==false) // Null kontrolü yaparak isDeleted false olanları filtrele
                .map(Article->modelMapper.map(Article, ArticleDto.class))
                .limit(4).collect(Collectors.toList());
        return articles;
    }

    @Override
    public List<ArticleHomeDto> searchArticles(String keyword) {

        List<Article> articles = articleRepository.findByTitle(keyword);
        List<ArticleHomeDto> articleHomeDtos = articles.stream()
               .filter(x -> x.getIsDeleted()==false) // Null kontrolü yaparak isDeleted false olanları filtrele
                .map(Article->modelMapper.map(Article, ArticleHomeDto.class))
                .collect(Collectors.toList());
        return articleHomeDtos;

    }

//    @Override
//    public Page<ArticleDto> getArticles(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Article> articlesPage = articleRepository.findAll(pageable);
//        return articlesPage.map(article -> modelMapper.map(article, ArticleDto.class));
//    }

    @Override
    public Page<ArticleDto> getArticles(int page, int size) {
        size = 6;
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlesPage = articleRepository.findAll(pageable);

        // Filter and map the articles to DTOs
        List<ArticleDto> articleDtos = articlesPage.stream()
                .filter(x -> x.getIsDeleted()==false)
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());

        // Create a new PageImpl from the list of ArticleDto
        return new PageImpl<>(articleDtos, pageable, articlesPage.getTotalElements());
    }

    @Override
    public void removeArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.setIsDeleted(true);
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(ArticleCreateDto articleCreateDto) {
        Article findArticle = articleRepository.findById(articleCreateDto.getId()).orElseThrow();

        Category category = categoryRepository.findById(articleCreateDto.getCategoryId()).orElseThrow();

        findArticle.setSubTitle(articleCreateDto.getSubTitle());
        findArticle.setDescription(articleCreateDto.getDescription());
        findArticle.setTitle(articleCreateDto.getTitle());
        findArticle.setImage(articleCreateDto.getImage());
        findArticle.setUpdated(new Date());
        findArticle.setCategory(category);

        articleRepository.saveAndFlush(findArticle);
    }

    @Override
    public ArticleCreateDto findUpdateArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleCreateDto articleCreateDto = modelMapper.map(article, ArticleCreateDto.class);
        return articleCreateDto;
    }

    @Override
    public ArticleHomeDto findHomeArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleHomeDto articleHomeDto = modelMapper.map(article, ArticleHomeDto.class);
        return articleHomeDto;
    }

    @Override
    public List<ArticleRelatedDto> getRelatedArticles() {
        return List.of();
    }


    @Override
    public ArticleDto getArticleById(Long id) {
        Article article = modelMapper.map(articleRepository.findById(id).get(), Article.class);
              return modelMapper.map(article, ArticleDto.class);
    }




//    public ArticleDto getArticlesByTitle(String title) {
//        Article article = modelMapper.map(articleRepository.findByTitleContainingIgnoreCase(title).get(), Article.class);
//        return modelMapper.map(article, ArticleDto.class);
//    }


}



