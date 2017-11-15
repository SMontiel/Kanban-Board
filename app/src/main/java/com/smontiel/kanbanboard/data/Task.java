package com.smontiel.kanbanboard.data;

/**
 * Created by Salvador Montiel on 8/11/17.
 */
public class Task {
    private int id;
    private String title;
    private int idColumn;

    public Task(int id, String title, int idColumn) {
        this.id = id;
        this.title = title;
        this.idColumn = idColumn;
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

    public int getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(int idColumn) {
        this.idColumn = idColumn;
    }
}
