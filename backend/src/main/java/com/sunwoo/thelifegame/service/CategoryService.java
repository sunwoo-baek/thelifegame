package com.sunwoo.thelifegame.service;

import com.sunwoo.thelifegame.model.Category;
import com.sunwoo.thelifegame.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // create or update category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // find category by id
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // get all categories for a user
    public List<Category> findByUserId(Long userId) {
        return categoryRepository.findByUserId(userId);
    }

    // get all sub-categories for a parent category
    public List<Category> findByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    // delete category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
