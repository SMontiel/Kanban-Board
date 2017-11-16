package com.smontiel.kanbanboard.data.local;

import com.smontiel.kanbanboard.data.Column;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.Task;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import ollie.query.Insert;
import ollie.query.Select;

/**
 * Created by Salvador Montiel on 16/11/17.
 */

public class LocalDataSource implements DataSource {
    private static final LocalDataSource ourInstance = new LocalDataSource();

    public static LocalDataSource getInstance() {
        return ourInstance;
    }

    private LocalDataSource() {}

    @Override
    public void addColumn(Column column) {
        ColumnEntity ce = new ColumnEntity();
        ce.title = column.getTitle();
        ce.save();
    }

    @Override
    public Observable<Task> getTasksFromColumn(long idColumn) {
        return Select.from(TaskEntity.class)
                .where("id_column", idColumn)
                .observable()
                .flatMap(new Function<List<TaskEntity>, ObservableSource<TaskEntity>>() {
                    @Override
                    public ObservableSource<TaskEntity> apply(List<TaskEntity> taskEntities) throws Exception {
                        return Observable.fromIterable(taskEntities);
                    }
                })
                .map(new Function<TaskEntity, Task>() {
                    @Override
                    public Task apply(TaskEntity taskEntity) throws Exception {
                        return new Task(taskEntity.id, taskEntity.title, taskEntity.column.id);
                    }
                });
    }

    @Override
    public Observable<Column> getColumns() {
        return Select.from(ColumnEntity.class)
                .observable()
                .flatMap(new Function<List<ColumnEntity>, ObservableSource<ColumnEntity>>() {
                    @Override
                    public ObservableSource<ColumnEntity> apply(List<ColumnEntity> columnEntities) throws Exception {
                        return Observable.fromIterable(columnEntities);
                    }
                })
                .map(new Function<ColumnEntity, Column>() {
                    @Override
                    public Column apply(ColumnEntity columnEntity) throws Exception {
                        return new Column(columnEntity.id, columnEntity.title);
                    }
                });
    }
}
