package com.codecool.web.model;

public class Work extends AbstractModel {
    private final String title;
    private final String content;
    private final int publishedDate;


    public Work(int id, String title, String content, int publishedDate) {
        super(id);
        this.title = title;
        this.content = content;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPublishedDate() {
        return publishedDate;
    }
}
