package com.spring.achareh.controller;

import com.spring.achareh.model.Category;
import com.spring.achareh.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestParam String name) {
        Category category = new Category(name);
        categoryService.save(category);
    }
}
