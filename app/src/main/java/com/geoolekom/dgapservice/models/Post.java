package com.geoolekom.dgapservice.models;

/**
 * Created by geoolekom on 06.12.16.
 */

public class Post {
    private int id;
    private String title;
    private String entry;
    private String author;

    public Post(int id, String title, String entry, String author) {
        this.title = title;
        this.entry = entry;
        this.author = author;
        this.id = id;
    }

    public int getId() {
        return id;
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
