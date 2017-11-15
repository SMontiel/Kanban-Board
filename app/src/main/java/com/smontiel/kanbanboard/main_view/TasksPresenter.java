package com.smontiel.kanbanboard.main_view;

import android.support.annotation.NonNull;

import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salvador Montiel on 14/11/17.
 */
class TasksPresenter implements TasksContract.Presenter {
    @NonNull
    private final TasksContract.View columnsView;
    @NonNull
    private final DataSource dataSource;

    public TasksPresenter(@NonNull TasksContract.View columnsView, @NonNull DataSource dataSource) {
        //TODO: Check not null of parameters
        this.columnsView = columnsView;
        this.dataSource = dataSource;

        this.columnsView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadTasks(int idColumn) {
        columnsView.setLoadingIndicator(true);

        List<Task> tasks = dataSource.getTasksFromColumn(idColumn);
        List<TaskItem> items = new ArrayList<>();
        for (Task t : tasks) {
            items.add(new TaskItem(t));
        }

        columnsView.showTasks(items);
        columnsView.setLoadingIndicator(false);
    }
}
