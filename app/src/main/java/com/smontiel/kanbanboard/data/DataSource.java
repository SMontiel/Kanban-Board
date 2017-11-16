package com.smontiel.kanbanboard.data;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public interface DataSource {

    void addColumn(Column column);

    Observable<Task> getTasksFromColumn(long idColumn);

    Observable<Column> getColumns();
}
