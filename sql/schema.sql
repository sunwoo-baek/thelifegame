-- =========================
-- The Life Game Schema v1.0
-- =========================

-- Create database
CREATE DATABASE IF NOT EXISTS the_life_game;
USE the_life_game;

-- ------------------------
-- Users table
-- ------------------------
CREATE TABLE if not exists users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100),
    password_hash varchar(100),
    created_at timestamp default current_timestamp
);

-- ------------------------
-- Categories table
-- ------------------------
CREATE TABLE if not exists categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    parent_id INT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    unit varchar(50), -- by what we will keep track of the task
    points_per_unit INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (parent_id) REFERENCES categories(id)
);

-- ------------------------
-- Tasks table
-- ------------------------ (consider making all of them optional)
CREATE TABLE if not exists tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    occurred_at DATETIME NOT NULL,
    duration_seconds INT NULL,
    quantity decimal null, -- flexible: minutes, reps, money, episodes, etc.
    points_earned int default 0, -- can be calculated on the fly
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- ---------------------------------------------------------------------
-- Indexes for performance. May need to comment out later if rerunning.
-- ---------------------------------------------------------------------
CREATE INDEX idx_categories_user ON categories(user_id);
CREATE INDEX idx_categories_parent ON categories(parent_id);
CREATE INDEX idx_tasks_user ON tasks(user_id);
CREATE INDEX idx_tasks_category ON tasks(category_id);
CREATE INDEX idx_tasks_occurred ON tasks(occurred_at);