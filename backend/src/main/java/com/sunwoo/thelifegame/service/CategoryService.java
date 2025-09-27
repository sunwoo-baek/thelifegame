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

    // save category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // update category
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            if (updatedCategory.getParent() != null) category.setParent(updatedCategory.getParent());
            if (updatedCategory.getUser() != null) category.setUser(updatedCategory.getUser());
            if (updatedCategory.getName() != null) category.setName(updatedCategory.getName());
            if (updatedCategory.getDescription() != null) category.setDescription(updatedCategory.getDescription());
            if (updatedCategory.getChildren() != null) category.setChildren(updatedCategory.getChildren());
            if (updatedCategory.getUnit() != null) category.setUnit(updatedCategory.getUnit());
            if (updatedCategory.getPointsPerUnit() != null) category.setPointsPerUnit(updatedCategory.getPointsPerUnit());
            // Add any other fields you want to allow updating

            return categoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
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
