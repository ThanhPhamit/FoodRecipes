package com.example.foodrecipes.Models;

import com.google.gson.annotations.SerializedName;

public class FoodTrivia {
    @SerializedName("text")
    private String foodTrivia;

    public FoodTrivia(String foodTrivia) {
        this.foodTrivia = foodTrivia;
    }

    public String getText() {
        return foodTrivia;
    }

    public void setText(String text) {
        this.foodTrivia = text;
    }
}
