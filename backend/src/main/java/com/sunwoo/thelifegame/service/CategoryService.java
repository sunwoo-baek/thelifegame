package com.sunwoo.thelifegame.service;

import com.sunwoo.thelifegame.model.Category;
import com.sunwoo.thelifegame.model.User;
import com.sunwoo.thelifegame.repository.UserRepository;
import com.sunwoo.thelifegame.repository.CategoryRepository;
import com.sunwoo.thelifegame.dto.CategoryResponse;
import com.sunwoo.thelifegame.dto.CategorySaveRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository; // To validate user existence

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    // save category
    public CategoryResponse saveCategory(CategorySaveRequest request) {
        Category category = mapToEntity(request);
        Category saved = categoryRepository.save(category);
        return mapToResponse(saved);
    }

    // ----------------- UPDATE -----------------
    public CategoryResponse updateCategory(Long id, CategorySaveRequest request) {
        Category updatedCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found with id " + id));

        // Update fields from request if present
        if (request.getName() != null) updatedCategory.setName(request.getName());
        if (request.getDescription() != null) updatedCategory.setDescription(request.getDescription());
        if (request.getUnit() != null) updatedCategory.setUnit(request.getUnit());
        if (request.getPointsPerUnit() != null) updatedCategory.setPointsPerUnit(request.getPointsPerUnit());

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + request.getUserId()));
            updatedCategory.setUser(user);
        }

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId()).orElse(null);
            updatedCategory.setParent(parent);
        }

        Category saved = categoryRepository.save(updatedCategory);
        return mapToResponse(saved);
    }

    // ----------------- FIND -----------------
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        return mapToResponse(category);
    }

    public List<CategoryResponse> findByUserId(Long userId) {
        return categoryRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<CategoryResponse> findByParentId(Long parentId) {
        return categoryRepository.findByParentId(parentId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ----------------- DELETE -----------------
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    // ----------------- MAPPERS -----------------
    private Category mapToEntity(CategorySaveRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + request.getUserId()));

        Category parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId()).orElse(null);
        }

        Category category = new Category();
        category.setUser(user);
        category.setParent(parent);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setUnit(request.getUnit());
        category.setPointsPerUnit(request.getPointsPerUnit());
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setUserId(category.getUser().getId());
        response.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        response.setUnit(category.getUnit());
        response.setPointsPerUnit(category.getPointsPerUnit());
        return response;
    }
}
