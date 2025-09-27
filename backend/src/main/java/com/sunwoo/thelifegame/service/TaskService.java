package com.sunwoo.thelifegame.service;

import com.sunwoo.thelifegame.model.Task;
import com.sunwoo.thelifegame.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // save task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // update task
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            if (updatedTask.getUser() != null) task.setUser(updatedTask.getUser());
            if (updatedTask.getCategory() != null) task.setCategory(updatedTask.getCategory());
            if (updatedTask.getTitle() != null) task.setTitle(updatedTask.getTitle());
            if (updatedTask.getDescription() != null) task.setDescription(updatedTask.getDescription());
            if (updatedTask.getOccurredAt() != null) task.setOccurredAt(updatedTask.getOccurredAt());
            if (updatedTask.getDurationSeconds() != null) task.setDurationSeconds(updatedTask.getDurationSeconds());
            if (updatedTask.getQuantity() != null) task.setQuantity(updatedTask.getQuantity());
            if (updatedTask.getPointsEarned() != null) task.setPointsEarned(updatedTask.getPointsEarned());
            // Add any other fields you want to allow updating

            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    // find task by id
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    // get all tasks for a user
    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // get all tasks for a category
    public List<Task> findByCategoryId(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    // delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}