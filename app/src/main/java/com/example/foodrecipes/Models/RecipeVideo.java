package com.example.foodrecipes.Models;

import com.google.gson.annotations.SerializedName;

public class RecipeVideo {
    private String title;
    private String shortTitle;
    private String youTubeId;
    private double rating;
    private int views;
    private String thumbnail;
    private int length;

    public RecipeVideo(String title, String shortTitle, String youTubeId, double rating, int views, String thumbnail, int length) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.youTubeId = youTubeId;
        this.rating = rating;
        this.views = views;
        this.thumbnail = thumbnail;
        this.length = length;
    }

    public RecipeVideo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
