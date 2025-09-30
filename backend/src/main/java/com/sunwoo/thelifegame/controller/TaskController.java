package com.sunwoo.thelifegame.controller;

import com.sunwoo.thelifegame.model.Task;
import com.sunwoo.thelifegame.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // save task
    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    // Update a task
    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }

    // Get all tasks for a user
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(@PathVariable("userId") Long userId) {
        return taskService.findByUserId(userId);
    }

    // Get all tasks for a category - Most common use.
    @GetMapping("/category/{categoryId}")
    public List<Task> getTasksByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return taskService.findByCategoryId(categoryId);
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}