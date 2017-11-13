package com.smontiel.kanbanboard;

/**
 * Created by Salvador Montiel on 8/11/17.
 */
public class Task {
    private String id;
    private String title;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
