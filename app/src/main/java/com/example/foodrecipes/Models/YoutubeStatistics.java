package com.example.foodrecipes.Models;

public class YoutubeStatistics {
    private String viewCount;
    private String likeCount;

    public YoutubeStatistics(String viewCount, String likeCount) {
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public YoutubeStatistics() {
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
