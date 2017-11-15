package com.smontiel.kanbanboard.data.fake;

import com.smontiel.kanbanboard.data.Column;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public class FakeDataSource implements DataSource {
    private static final FakeDataSource ourInstance = new FakeDataSource();

    public static FakeDataSource getInstance() {
        return ourInstance;
    }

    private FakeDataSource() {}

    @Override
    public List<Task> getTasksFromColumn(int idColumn) {
        List<Task> list = new ArrayList<>();
        String title = getTitleFromId(idColumn);
        for (int i = 0; i < new Random().nextInt(10); i++) {
            list.add(new Task(""+i, title + " " + (i+1)));
        }

        return list;
    }

    @Override
    public List<Column> getColumns() {
        List<Column> list = new ArrayList<>();
        list.add(new Column(1, "Backlog"));
        list.add(new Column(2, "ToDo"));
        list.add(new Column(3, "Work in progress"));
        list.add(new Column(4, "Done"));

        return list;
    }

    private String getTitleFromId(int id) {
        for (Column c : getColumns()) {
            if (c.getId() == id) return c.getTitle();
        }
        return "Task";
    }
}
