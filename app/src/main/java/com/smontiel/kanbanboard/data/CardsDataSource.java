package com.smontiel.kanbanboard.data;

import java.util.List;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public interface CardsDataSource {

    List<Task> getTasksFromColumn(int idColumn);

    List<Column> getColumns();
}
