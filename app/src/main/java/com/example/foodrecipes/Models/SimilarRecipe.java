package com.example.foodrecipes.Models;

public class SimilarRecipe {
    private int id;
    private String title;
    private String imageType;
    private int readyInMinutes;
    private int servings;

    public SimilarRecipe(int id, String title, String imageType, int readyInMinutes, int servings) {
        this.id = id;
        this.title = title;
        this.imageType = imageType;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
