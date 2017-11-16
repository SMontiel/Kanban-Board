package com.smontiel.kanbanboard.data;

/**
 * Created by Salvador Montiel on 8/11/17.
 */
public class Task {
    private long id;
    private String title;
    private long idColumn;

    public Task(String title, long idColumn) {
        this.title = title;
        this.idColumn = idColumn;
    }

    public Task(long id, String title, long idColumn) {
        this.id = id;
        this.title = title;
        this.idColumn = idColumn;
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

    public long getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(long idColumn) {
        this.idColumn = idColumn;
    }
}
