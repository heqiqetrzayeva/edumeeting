package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.articledtos.ArticleCreateDto;
import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.services.ArticleService;
import com.example.edumeeting.services.CategoryService;
import com.example.edumeeting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads";


    @GetMapping("/admin/article")
    public String articleGet(Model model) {
        List<ArticleDto> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "/admin/article/article";
    }


    @GetMapping("/admin/article/create")
    public String articleCreate(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "/admin/article/create";
    }


    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreateDto articleCreateDto, @RequestParam("image") MultipartFile image) throws IOException {
        UUID rand = UUID.randomUUID();
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, rand + image.getOriginalFilename());
        fileNames.append(image.getOriginalFilename());
        Files.write(fileNameAndPath, image.getBytes());

        articleCreateDto.setImage(rand + image.getOriginalFilename());
        articleService.addArticle(articleCreateDto);
        return "redirect:/admin/article";
    }


    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        ArticleDto article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@ModelAttribute @PathVariable Long id){
        articleService.removeArticle(id);
        return "redirect:/admin/article";
    }


    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@ModelAttribute @PathVariable Long id, Model model){
        ArticleCreateDto articleCreateDto = articleService.findUpdateArticle(id);
        List<CategoryDto> categories =  categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("article", articleCreateDto);
        return "/admin/article/update";
    }


    @PostMapping("/admin/article/update/{id}")
    public String updateArticle(@ModelAttribute ArticleCreateDto articleCreateDto){

        articleService.updateArticle(articleCreateDto);
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model){

        List<UserDto> userList = userService.getDashboardUsers();
        model.addAttribute("users", userList);
        return "/admin/auth/user-list";
    }

    @GetMapping("/admin/users/role/{id}")
    public String updateRoles(@PathVariable Long id, Model model){

        List<UserDto> userList = userService.getDashboardUsers();
        model.addAttribute("users", userList);
        return "/admin/auth/user-list";
    }


}
