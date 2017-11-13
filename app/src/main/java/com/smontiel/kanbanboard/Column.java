package com.smontiel.kanbanboard;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public class Column {
    private int id;
    private String title;

    public Column(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
