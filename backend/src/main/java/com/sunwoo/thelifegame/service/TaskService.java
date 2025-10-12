package com.sunwoo.thelifegame.service;

import com.sunwoo.thelifegame.model.Task;
import com.sunwoo.thelifegame.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.sunwoo.thelifegame.dto.TaskSaveRequest;
import com.sunwoo.thelifegame.dto.TaskResponse;
import com.sunwoo.thelifegame.model.User;
import com.sunwoo.thelifegame.model.Category;
import com.sunwoo.thelifegame.repository.UserRepository;
import com.sunwoo.thelifegame.repository.CategoryRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository; // To validate user existence
    private final CategoryRepository categoryRepository; // To validate category existence

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // save task
    public TaskResponse saveTask(TaskSaveRequest request) {
        Task task = mapToEntity(request);
        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    // add logic to update parent category's total points upon creation of a task.
    // add logic to update user's total points upon creation of a task.
    // Make sure to account for nulls. Nulls means 0 for now.

    // ----------------- UPDATE -----------------
    public TaskResponse updateTask(Long id, TaskSaveRequest request) {
        Task existingTask = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id " + id));

        if (request.getTitle() != null) existingTask.setTitle(request.getTitle());
        if (request.getDescription() != null) existingTask.setDescription(request.getDescription());
        if (request.getOccurredAt() != null) existingTask.setOccurredAt(request.getOccurredAt());
        if (request.getDurationSeconds() != null) existingTask.setDurationSeconds(request.getDurationSeconds());
        if (request.getQuantity() != null) existingTask.setQuantity(request.getQuantity());
        if (request.getPointsPerUnit() != null) existingTask.setPointsPerUnit(request.getPointsPerUnit());
        // Add any other fields you want to allow updating

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + request.getUserId()));
            existingTask.setUser(user);
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            existingTask.setCategory(category);
        }

        Task saved = taskRepository.save(existingTask);
        return mapToResponse(saved);
    }

    // ----------------- FIND -----------------
    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        return mapToResponse(task);
    }

    public List<TaskResponse> findByUserId(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(this::mapToResponse).toList();
    }

    public List<TaskResponse> findByCategoryId(Long categoryId) {
        List<Task> tasks = taskRepository.findByCategoryId(categoryId);
        return tasks.stream().map(this::mapToResponse).toList();
    }

    // ---------------- DELETE -----------------
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // ---------------- MAPPERS -----------------
    private Task mapToEntity(TaskSaveRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + request.getUserId()));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id " + request.getCategoryId()));
        Task task = new Task();
        task.setUser(user);
        task.setCategory(category);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setOccurredAt(request.getOccurredAt());
        task.setDurationSeconds(request.getDurationSeconds());
        task.setQuantity(request.getQuantity() != null ? request.getQuantity() : 1); // default to 1 if null
        task.setPointsPerUnit(request.getPointsPerUnit() != null ? request.getPointsPerUnit() : 0); // default to 0 if null
        return task;
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setUserId(task.getUser().getId());
        response.setCategoryId(task.getCategory().getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setOccurredAt(task.getOccurredAt());
        response.setDurationSeconds(task.getDurationSeconds());
        response.setQuantity(task.getQuantity());
        response.setPointsPerUnit(task.getPointsPerUnit());
        response.setCreatedAt(task.getCreatedAt());
        return response;
    }
}