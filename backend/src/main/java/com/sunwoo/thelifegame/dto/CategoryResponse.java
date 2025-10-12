package com.sunwoo.thelifegame.dto;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private Long userId;
    private Long parentId; // Optional, can be null for root categories
    private String name;
    private String description;
    private String unit; // e.g., "hours", "items"
    private Integer pointsPerUnit;
    private Integer totalPoints;
}
