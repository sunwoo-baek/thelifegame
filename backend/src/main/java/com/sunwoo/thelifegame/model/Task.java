package com.sunwoo.thelifegame.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Consider EAGER if used often, but beware of performance.
    @ManyToOne(fetch = FetchType.LAZY)  // Many categories → one user
    @JoinColumn(name = "user_id", nullable = false) // column in categories table
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER may be better
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "points_earned") // This could potentially be calculated on the fly
    private Integer pointsEarned;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructors
    public Task() {} // for JPA

    public Task(User user, Category category, String title, String description,
                LocalDateTime occurredAt, Integer durationSeconds, Integer quantity, Integer pointsEarned) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.description = description;
        this.occurredAt = occurredAt;
        this.durationSeconds = durationSeconds;
        this.quantity = quantity;
        this.pointsEarned = pointsEarned;
        this.createdAt = LocalDateTime.now();
    }

    // consider adding more constructors for simpler tasks

    // getters and setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }

    public Integer getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getPointsEarned() { return pointsEarned; }
    public void setPointsEarned(Integer pointsEarned) { this.pointsEarned = pointsEarned; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}