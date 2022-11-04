package com.example.foodrecipes.Models;

import com.google.gson.annotations.SerializedName;

public class RecipeCardRespone {
    @SerializedName("url")
    private String recipeCardUrl;

    public String getRecipeCardUrl() {
        return recipeCardUrl;
    }

    public void setRecipeCardUrl(String recipeCardUrl) {
        this.recipeCardUrl = recipeCardUrl;
    }

    public RecipeCardRespone(String recipeCardUrl) {
        this.recipeCardUrl = recipeCardUrl;
    }
}
