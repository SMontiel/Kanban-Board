package com.smontiel.kanbanboard.data;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public class Column {
    private long id;
    private String title;

    public Column(String title) {
        this.title = title;
    }

    public Column(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
