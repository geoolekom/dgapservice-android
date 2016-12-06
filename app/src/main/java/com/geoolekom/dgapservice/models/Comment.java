package com.geoolekom.dgapservice.models;

/**
 * Created by geoolekom on 06.12.16.
 */

public class Comment {
    private String author;
    private String entry;

    public Comment(String author, String entry) {
        this.author = author;
        this.entry = entry;
    }

    public String getAuthor() {
        return author;
    }

    public String getEntry() {
        return entry;
    }
}
