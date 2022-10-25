package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.SimilarRecipe;

import java.util.ArrayList;

public interface SimilarRecipesListener {
    void didFetch(ArrayList<SimilarRecipe> response, String message);
    void didError(String message);
}
