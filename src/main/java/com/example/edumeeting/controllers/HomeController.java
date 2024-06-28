package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.articledtos.ArticleCreateDto;
import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.articledtos.ArticleRelatedDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.commentdtos.CommentCreateDto;
import com.example.edumeeting.dtos.commentdtos.CommentDto;
import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyCreateDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import com.example.edumeeting.models.Comment;
import com.example.edumeeting.models.Contact;
import com.example.edumeeting.repositories.UserRepository;
import com.example.edumeeting.services.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository repo;

    @Autowired
    private CommentService commentService;


    @GetMapping("/")
    public String home(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<ArticleDto> articles = articleService.getTwoArticles();
        model.addAttribute("articles", articles);
        return "home";
    }


    @GetMapping("/meetings")
    public String meetings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<ArticleDto> articlesPage = articleService.getArticles(page, size);
        model.addAttribute("articlesPage", articlesPage);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("articles", articlesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlesPage.getTotalPages());
        model.addAttribute("size", size);
        return "meetings";
    }


    @GetMapping("/details/{id}/{seoUrl}")
    public String details(@PathVariable Long id, Model model) {
        ArticleHomeDto article = articleService.findHomeArticle(id);
        model.addAttribute("article", article);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        List<CommentDto> commentDto = commentService.getCommentsByArticleId(id);
        model.addAttribute("comments", commentDto);
//        List<ArticleRelatedDto> articleRelated = articleService.getRelatedArticles();
//        model.addAttribute("related", articleRelated);

        List<ContactDto> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);

//        ArticleDetailDto articleDetail = articleService.getDetail(id);
//        model.addAttribute("article",articleDetail);
        return "meeting-details";
    }

    @PostMapping("/details/{id}/{seoUrl}")
    public String addComment(CommentCreateDto commentCreate,Principal principal, @PathVariable Long id, @PathVariable String seoUrl, @PathVariable Long parentId){

        if (principal != null) {
            String username = principal.getName();
            commentCreate.setArticleId(id);
            Comment reply = new Comment();
            commentService.addReply(parentId, reply);

            try {
                commentService.addComment(commentCreate, username);
                return "redirect:/details/" + commentCreate.getArticleId() + "/" + seoUrl;
            } catch (Exception e) {
                return "error";
            }
        } else {
            return "redirect:login"; // Kullanıcı giriş yapmamışsa login sayfasına yönlendir
        }
    }


//    @PostMapping("/course/{id}/{seoUrl}")
//    public String addComment(CommentCreateDto commentCreateDto, Principal principal, @PathVariable Long id) {
//        String username = principal.getName();
//        commentCreateDto.setArticleId(id);
//        commentService.addComment(commentCreateDto, username);
//        return "redirect:course";
//    }


//    @GetMapping("/vacancy-details/{id}")
//    public String vacancyDetails(@PathVariable Long id, Model model) {
//        VacancyDto vacancy = vacancyService.(id);
//        model.addAttribute("article", article);
//        List<CategoryDto> categories = categoryService.getAllCategories();
//        model.addAttribute("categories", categories);
//        List<ContactDto> contacts = contactService.getAllContacts();
//        model.addAttribute("contacts", contacts);
//        return "meeting-details";
//    }






    @GetMapping("/course")
    public String course(Model model){
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "course";
    }

    @PostMapping("/course")
    public String about(String keyword, Model model){
        List<CategoryDto> categories = categoryService.searchCategories(keyword);
        model.addAttribute("categories", categories);
        return "course";
    }

//    @GetMapping("/details/{id}/{seoUrl")
//    public String detail(Model model, @PathVariable Long id)
//    {
//        ArticleDetailDto articleDetail = articleService.getDetail(id);
//        List<CommentDto> commentDto = commentService.getCommentsByArticleId(id);
////        List<ArticleTrendVideoDto> trends = articleService.getTrendVideos();
////        List<ArticleRelatedDto> articleRelated = articleService.getRelatedArticles(articleDetail.getCategory().getId());
//        model.addAttribute("article",articleDetail);
//        model.addAttribute("comments",commentDto);
////        model.addAttribute("trends", trends);
////        model.addAttribute("related",articleRelated);
//        return "/meeting-details";
//    }









}
