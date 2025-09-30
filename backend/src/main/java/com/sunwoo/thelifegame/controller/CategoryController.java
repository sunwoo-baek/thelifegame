package com.sunwoo.thelifegame.controller;

import com.sunwoo.thelifegame.dto.CategorySaveRequest;
import com.sunwoo.thelifegame.service.CategoryService;
import com.sunwoo.thelifegame.dto.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CategoryResponse saveCategory(@RequestBody CategorySaveRequest category) {
        return categoryService.saveCategory(category);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    // get all categories for a user
    @GetMapping("/user/{userId}")
    public List<CategoryResponse> getCategoriesByUserId(@PathVariable("userId") Long userId) {
        return categoryService.findByUserId(userId);
    }

    // get all sub-categories for a parent category
    @GetMapping("/parent/{parentId}")
    public List<CategoryResponse> getSubCategoriesByParentId(@PathVariable("parentId") Long parentId) {
        return categoryService.findByParentId(parentId);
    }

    // Update category
    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable("id") Long id, @RequestBody CategorySaveRequest updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
    }
}