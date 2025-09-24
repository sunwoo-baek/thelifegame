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

    // create or update task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
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
