package com.example.foodrecipes.Listeners;


import com.example.foodrecipes.Models.YoutubeVideoResponse;

public interface YoutubeVideosResponseListener {
    void didFetch(YoutubeVideoResponse response, String message);
    void didError(String message);
}
