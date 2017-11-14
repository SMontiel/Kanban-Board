package com.smontiel.kanbanboard.data;

import java.util.List;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public interface DataSource {

    List<Task> getTasksFromColumn(int idColumn);

    List<Column> getColumns();
}
