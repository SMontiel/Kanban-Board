package com.smontiel.kanbanboard.data;

import com.smontiel.kanbanboard.Column;
import com.smontiel.kanbanboard.Task;

import java.util.List;

/**
 * Created by Salvador Montiel on 13/11/17.
 */
public interface CardsRepository {

    List<Task> getTasksFromColumn(int idColumn);

    List<Column> getColumns();
}
