package com.example.foodrecipes.Models;

public class Localized {
    private String title;
    private String description;

    public Localized(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Localized() {
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
}
