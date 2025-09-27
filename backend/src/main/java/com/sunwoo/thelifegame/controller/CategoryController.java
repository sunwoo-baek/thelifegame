package com.sunwoo.thelifegame.controller;

import com.sunwoo.thelifegame.model.Category;
import com.sunwoo.thelifegame.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // save category
    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    // get all categories for a user
    @GetMapping("/user/{userId}")
    public List<Category> getCategoriesByUserId(@PathVariable Long userId) {
        return categoryService.findByUserId(userId);
    }

    // get all sub-categories for a parent category
    @GetMapping("/parent/{parentId}")
    public List<Category> getSubCategoriesByParentId(@PathVariable Long parentId) {
        return categoryService.findByParentId(parentId);
    }

    // Update category
    @PatchMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}