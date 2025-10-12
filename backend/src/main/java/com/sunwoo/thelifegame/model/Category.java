package com.sunwoo.thelifegame.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Consider EAGER if used often, but beware of performance.
    @ManyToOne(fetch = FetchType.LAZY)  // Many categories → one user
    @JoinColumn(name = "user_id", nullable = false) // column in categories table
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")  // nullable because root categories have no parent
    private Category parent;

    // Child categories
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children = new ArrayList<>();

    @Column(nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description = "";

    @Column(length = 100)
    private String unit;

    @Column(name = "points_per_unit")
    private Integer pointsPerUnit = 0;

    @Column(name = "total_points")
    private Integer totalPoints = 0;

    // Constructors
    public Category() {} // for JPA

    // main category
    public Category(String name, String description, User user, String unit, Integer pointsPerUnit) {
        this(name, description, user, null, unit, pointsPerUnit);
    }

    // sub category
    public Category(String name, String description, User user, Category parent, String unit, int pointsPerUnit) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.parent = parent;
        this.unit = unit;
        this.pointsPerUnit = pointsPerUnit;
    }

    // getters and setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Category getParent() { return parent; }
    public void setParent(Category parent) { this.parent = parent; }

    public List<Category> getChildren() { return children; }
    public void setChildren(List<Category> children) { this.children = children; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Integer getPointsPerUnit() { return pointsPerUnit; }
    public void setPointsPerUnit(Integer pointsPerUnit) { this.pointsPerUnit = pointsPerUnit; }

    public Integer getTotalPoints() { return totalPoints; }
    public void setTotalPoints(Integer totalPoints) { this.totalPoints = totalPoints; }

}