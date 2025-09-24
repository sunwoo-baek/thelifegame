package com.sunwoo.thelifegame.repository;

import com.sunwoo.thelifegame.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByCategoryId(Long categoryId);
    List<Task> findByUserIdAndCategoryId(Long userId, Long categoryId);
}
