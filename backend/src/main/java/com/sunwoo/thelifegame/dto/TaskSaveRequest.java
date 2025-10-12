package com.sunwoo.thelifegame.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskSaveRequest {
    private Long userId;
    private Long categoryId; // might consider making it optional
    private String title;
    private String description;
    private LocalDateTime occurredAt;
    private Integer durationSeconds;
    private Integer quantity;
    private Integer pointsPerUnit;
}
