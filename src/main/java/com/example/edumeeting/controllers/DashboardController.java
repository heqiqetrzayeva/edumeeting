package com.example.edumeeting.controllers;


import com.example.edumeeting.dtos.blogdtos.BlogCreateDto;
import com.example.edumeeting.services.ArticleService;
import com.example.edumeeting.services.BlogService;
import com.example.edumeeting.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/admin")
    public String index() {
        return "/admin/index";
    }


    @GetMapping("/admin/blog/create")
    public String blogCreate(Model model) {
        return "/admin/blog/create";
    }

    @PostMapping("/admin/blog/create")
    public String blogCreate(@ModelAttribute BlogCreateDto blogCreateDto) {
        blogService.addBlog(blogCreateDto);
        return "redirect:/admin/blog";
    }


//    @GetMapping("/admin/article/search")
//    public String searchArticles(
//            @RequestParam(value="keyword", required = false, defaultValue = "")
//            String keyword, Model model) {
//        List<ArticleDto> articles = articleService.searchArticles(keyword);
//        model.addAttribute("articles", articles);
//        model.addAttribute("keyword", keyword);
//        return "/admin/article/search";
//    }





//    @GetMapping({"/article"})
//    public List<ArticleDto> getAllArticles(@RequestParam(defaultValue = "0") int pageNumber,
//                                           @RequestParam(defaultValue = "") String searchKey) {
//        return articleService.getAllArticles(pageNumber, searchKey);
//    }

}
