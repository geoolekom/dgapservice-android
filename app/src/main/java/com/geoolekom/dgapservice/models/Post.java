package com.geoolekom.dgapservice.models;

/**
 * Created by geoolekom on 06.12.16.
 */

public class Post {
    private String title;
    private String entry;
    private String author;

    public Post(String title, String entry, String author) {
        this.title = title;
        this.entry = entry;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getEntry() {
        return entry;
    }

    public String getAuthor() {
        return author;
    }
}
