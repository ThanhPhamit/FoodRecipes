package com.example.foodrecipes.Models;

public class YoutubeVideo {
    private String id;
    private YoutubeSnippet snippet;
    private YoutubeStatistics statistics;

    public YoutubeVideo() {
    }

    public YoutubeVideo(String id, YoutubeSnippet snippet, YoutubeStatistics statistics) {
        this.id = id;
        this.snippet = snippet;
        this.statistics = statistics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public YoutubeSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(YoutubeSnippet snippet) {
        this.snippet = snippet;
    }

    public YoutubeStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(YoutubeStatistics statistics) {
        this.statistics = statistics;
    }
}
