package com.example.edumeeting.controllers;

import com.example.edumeeting.dtos.applicationdtos.ApplicationDto;
import com.example.edumeeting.dtos.articledtos.ArticleCreateDto;
import com.example.edumeeting.dtos.articledtos.ArticleDto;
import com.example.edumeeting.dtos.articledtos.ArticleHomeDto;
import com.example.edumeeting.dtos.blogdtos.BlogDto;
import com.example.edumeeting.dtos.categorydtos.CategoryDto;
import com.example.edumeeting.dtos.contactdtos.ContactCreateDto;
import com.example.edumeeting.dtos.contactdtos.ContactDto;
import com.example.edumeeting.dtos.userdtos.UserCreateDto;
import com.example.edumeeting.dtos.userdtos.UserDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyCreateDto;
import com.example.edumeeting.dtos.vacancydtos.VacancyDto;
import com.example.edumeeting.repositories.UserRepository;
import com.example.edumeeting.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private VacancyService vacancyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository repo;


    @GetMapping("/")
    public String home(Model model,@Param("keyword") String keyword ) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<ArticleDto> articles = articleService.getTwoArticles();
        model.addAttribute("articles", articles);
        List<BlogDto> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs",blogs);
        return "home";
    }


    @GetMapping("/meetings")
    public String meetings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {
        Page<ArticleDto> articlesPage = articleService.getArticles(page, size);
        model.addAttribute("articlesPage", articlesPage);
        List<ArticleHomeDto> articles = articleService.getHomeArticles();
        model.addAttribute("articles",articles);

        Page<ApplicationDto> applicationsPage = applicationService.getApplications(page, size);
        model.addAttribute("applications", applicationsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", applicationsPage.getTotalPages());
        return "meetings";
    }


    @GetMapping("/details/{id}/{seoUrl}")
    public String details(@PathVariable Long id, Model model) {
        ArticleHomeDto article = articleService.findHomeArticle(id);
        model.addAttribute("article", article);
        return "meeting-details";
    }


    @GetMapping("/vacancy")
    public String vacancy(Model model) {
        List<VacancyDto> vacancyDtos = vacancyService.getAllVacancies();
        model.addAttribute("vacancies",vacancyDtos);
        return "vacancy";
    }

    @GetMapping("/admin/vacancy/create")
    public String vacancyCreate() {
        return "/admin/vacancy/create";
    }


    @PostMapping("/admin/vacancy/create")
    public String vacancyCreate(@ModelAttribute VacancyCreateDto vacancyCreateDto)
    {
        vacancyService.addVacancy(vacancyCreateDto);
        return "redirect:vacancy";
    }




//    @GetMapping("/vacancy/details/{id}/")
//    public String vacancyDetails(@PathVariable Long id, Model model) {
//        VacancyCreateDto vacancies = vacancyService.fi(id);
//        model.addAttribute("vacancies", vacancies);
//        return "vacancy-details";
//    }

    @GetMapping("/blog")
    public String blogs(Model model) {
        List<BlogDto> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs",blogs);
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id, Model model) {
        BlogDto blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @PostMapping("/")
    public String search(String searchTerm, Model model) {
        List<ArticleHomeDto> articles = articleService.searchArticles(searchTerm);
        model.addAttribute("articles", articles);
        return "home";
    }



//    @GetMapping("/admin/blog/remove/{id}")
//    public String removeArticle(@ModelAttribute @PathVariable Long id){
//        blogService.removeArticle(id);
//        return "redirect:/admin/blog";
//    }


    @GetMapping("/progress")
    public String progress(Model model) {
        List<ArticleDto> articles = articleService.getAllArticles();
        model.addAttribute("articles",articles);
        return "/admin/progress";
    }

    @GetMapping("/contact")
    public String getContact(Model model) {
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<ContactDto> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contact";
    }

    @PostMapping("/contact")
    public String createContact(@ModelAttribute ContactCreateDto contactCreateDto) {
        contactService.addContact(contactCreateDto);
        return "redirect:meetings/details";
    }

    @GetMapping("/student")
    public String studenta(){
        return "/student/student";
    }


    @GetMapping("/marks")
    public String marks(){
//        List<CategoryDto> categories = categoryService.getAllCategories();
//        model.addAttribute("categories", categories);
//
//        Page<ApplicationDto> applicationsPage = applicationService.getApplications(page, size);
//        model.addAttribute("applications", applicationsPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", applicationsPage.getTotalPages());
        return "/student/marks";
    }

}
