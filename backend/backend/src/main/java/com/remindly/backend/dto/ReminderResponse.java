package com.remindly.backend.dto;

import java.time.LocalDateTime;

public class ReminderResponse {

    private Long id;

    private String title;

    private String description;

    private String link;

    private LocalDateTime deadline;

    private boolean favorite;

    private boolean completed;

    private LocalDateTime createdAt;


    public ReminderResponse(
            Long id,
            String title,
            String description,
            String link,
            LocalDateTime deadline,
            boolean favorite,
            boolean completed,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.deadline = deadline;
        this.favorite = favorite;
        this.completed = completed;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}