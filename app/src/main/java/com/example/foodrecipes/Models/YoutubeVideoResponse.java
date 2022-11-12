package com.example.foodrecipes.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class YoutubeVideoResponse {
    @SerializedName("items")
    public ArrayList<YoutubeVideo> youtubeVideos;
}
