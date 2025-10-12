package com.sunwoo.thelifegame.dto;
import lombok.Data;

@Data
public class CategorySaveRequest {
    private Long userId;
    private Long parentId; // Optional, can be null for root categories
    private String name;
    private String description;
    private String unit; // e.g., "hour", "item", "session"
    private Integer pointsPerUnit;
    private Integer totalPoints;
}
