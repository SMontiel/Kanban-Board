package com.smontiel.kanbanboard.data.local;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Created by Salvador Montiel on 16/11/17.
 */
@Table("tasks")
public class TaskEntity extends Model {
    @Column("title")
    public String title;
    @Column("id_column")
    public ColumnEntity column;
}
