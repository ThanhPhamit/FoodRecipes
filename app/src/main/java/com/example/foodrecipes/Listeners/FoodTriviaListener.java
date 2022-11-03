package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.FoodTrivia;

public interface FoodTriviaListener {
    void didFetch(FoodTrivia response, String message);
    void didError(String message);
}
