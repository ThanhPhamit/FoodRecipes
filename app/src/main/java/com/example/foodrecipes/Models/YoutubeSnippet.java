package com.example.foodrecipes.Models;

import java.util.Date;

public class YoutubeSnippet {
    private Date publishedAt;
    private String channelId;
    private String channelTitle;
    private Localized localized;

    public YoutubeSnippet() {
    }

    public YoutubeSnippet(Date publishedAt, String channelId, String channelTitle, Localized localized) {
        this.publishedAt = publishedAt;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.localized = localized;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }
}
