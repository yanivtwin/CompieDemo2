package com.example.yantwin.compiedemo.model;

import com.google.gson.annotations.Expose;


/*
the child class will contain thumb image title and link for youtube video
 */

public class Video {
    public Video() {
    }

    @Expose
    private String Title;
    @Expose
    private String link;
    @Expose
    private String thumb;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
