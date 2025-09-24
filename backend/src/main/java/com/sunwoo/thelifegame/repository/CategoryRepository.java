package com.sunwoo.thelifegame.repository;

import com.sunwoo.thelifegame.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserId(Long userId);
    List<Category> findByParentId(Long parentId);
}
