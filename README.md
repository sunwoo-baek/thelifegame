# Progress Tracker Backend System

A REST API backend system to help track progress across all areas of life.

## Overview
This project aims to give people a simple, centralized way to stay in control of their life: health, work, money, fitness, habits, hobbies — anything.  
It provides a flexible structure where users can create categories, sub-categories, and log tasks to measure improvement over time.

## Tech Stack
- **Languages:** Java, SQL  
- **Framework:** Spring Boot  
- **Database:** MySQL (developed using MySQL Workbench)

## Features

### Implemented
- Full CRUD for:
  - **Users**
  - **Categories**
  - **Tasks**
- **Hierarchical Categories**  
  - Categories can reference parent categories (self-referencing).
  - Supports multiple layers of nested categories (e.g., Fitness → Running → Endurance Running).
- **Task Logging System**  
  - Each task belongs to a category.  
  - Totals roll up through parent categories.
  - Supports retrieving tasks by user or category.
- **Endpoints for:**
  - A user’s categories
  - Categories inside a category
  - Tasks under a category
  - Tasks belonging to a user

### Planned
- Frontend visualizations showing progress and stats per category and subcategory.
- Cleaner UI flows for logging tasks quickly.

## API Overview
All endpoints follow standard REST CRUD patterns.  
They’ve been tested and validated using Postman.

**Core Routes Include:**
- `/api/users` – create, update, delete, list  
- `/api/categories` – category CRUD, nested category lookups  
- `/api/tasks` – task CRUD, logging tasks, fetching by user/category

## Architecture
Standard Spring Boot layered architecture:
- **Model Layer** – Entities for User, Category, Task  
- **Service Layer** – Business logic, category rollup logic  
- **Controller Layer** – REST endpoints  
- **DTOs** – Used for input/output formatting  

MySQL stores all relationships, including the self-referencing category table.

## Setup & Running
1. **Clone the repo**
2. **Set up MySQL schema**
3. **Configure environment variables**  
   - DB URL  
   - Username  
   - Password  
4. **Build & run**
   ```bash
   mvn spring-boot:run
