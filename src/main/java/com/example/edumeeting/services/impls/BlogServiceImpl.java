package com.example.edumeeting.services.impls;


import com.example.edumeeting.dtos.blogdtos.BlogCreateDto;
import com.example.edumeeting.dtos.blogdtos.BlogDto;
import com.example.edumeeting.models.Blog;
import com.example.edumeeting.repositories.BlogRepository;
import com.example.edumeeting.services.BlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BlogDto> getAllBlogs() {
        List<BlogDto> blogs = blogRepository.findAll().stream()
                .map(Blog->modelMapper.map(Blog, BlogDto.class))
                .collect(Collectors.toList());
        return blogs;
    }

    @Override
    public void addBlog(BlogCreateDto blogCreateDto) {
        Blog blog = new Blog();
        blog.setTitle(blogCreateDto.getTitle());
        blog.setSubTitle(blogCreateDto.getSubTitle());
        blog.setImage(blogCreateDto.getImage());
        blog.setDescription(blogCreateDto.getDescription());
        blog.setUpdated(new Date());
        blog.setCreated(new Date());
        blogRepository.save(blog);

    }

    @Override
    public BlogDto getBlogById(Long id) {
        Blog blog = modelMapper.map(blogRepository.findById(id).get(), Blog.class);
              return modelMapper.map(blog, BlogDto.class);
    }


//    public ArticleDto getArticlesByTitle(String title) {
//        Article article = modelMapper.map(articleRepository.findByTitleContainingIgnoreCase(title).get(), Article.class);
//        return modelMapper.map(article, ArticleDto.class);
//    }


}



