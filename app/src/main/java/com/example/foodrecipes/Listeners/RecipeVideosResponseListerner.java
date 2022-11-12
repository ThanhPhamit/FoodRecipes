package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.Instruction;
import com.example.foodrecipes.Models.RecipeVideoResponse;

import java.util.ArrayList;

public interface RecipeVideosResponseListerner {
    void didFetch(RecipeVideoResponse response, String message);
    void didError(String message);
}
