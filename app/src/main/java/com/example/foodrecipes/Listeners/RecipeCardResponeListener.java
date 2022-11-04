package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.RecipeCardRespone;

public interface RecipeCardResponeListener {
    void didFetch(RecipeCardRespone response, String message);
    void didError(String message);
}
