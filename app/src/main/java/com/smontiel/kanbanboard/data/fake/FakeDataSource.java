package com.smontiel.kanbanboard.data.fake;

import com.smontiel.kanbanboard.data.Column;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

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
    public void addColumn(Column column) {
        throw new IllegalStateException("No implementado");
    }

    @Override
    public void addTask(Task task) {
        throw new IllegalStateException("No implementado");
    }

    @Override
    public Observable<Task> getTasksFromColumn(final long idColumn) {

        return getColumns()
                .filter(new Predicate<Column>() {
                    @Override
                    public boolean test(Column column) throws Exception {
                        return column.getId() == idColumn;
                    }
                })
                .map(new Function<Column, List<Task>>() {
                    @Override
                    public List<Task> apply(Column column) throws Exception {
                        List<Task> list = new ArrayList<>();
                        for (int i = 0; i < new Random().nextInt(10); i++) {
                            String taskTitle = column.getTitle() + " " + (i+1);
                            list.add(new Task(i, taskTitle, column.getId()));
                        }
                        return list;
                    }
                }).flatMap(new Function<List<Task>, ObservableSource<Task>>() {
                    @Override
                    public ObservableSource<Task> apply(List<Task> tasks) throws Exception {
                        return Observable.fromIterable(tasks);
                    }
                });
    }

    @Override
    public Observable<Column> getColumns() {
        final List<Column> list = new ArrayList<>();
        list.add(new Column(1, "Backlog"));
        list.add(new Column(2, "ToDo"));
        list.add(new Column(3, "Work in progress"));
        list.add(new Column(4, "Done"));

        return Observable.fromIterable(list);
    }
}
