package com.sunwoo.thelifegame.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private LocalDateTime occurredAt;
    private Integer durationSeconds;
    private Integer quantity;
    private Integer pointsPerUnit;
    private LocalDateTime createdAt;
}
