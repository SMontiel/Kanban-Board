package com.smontiel.kanbanboard.data.local;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Created by Salvador Montiel on 16/11/17.
 */
@Table("columns")
public class ColumnEntity extends Model {
    @Column("title")
    public String title;
}
