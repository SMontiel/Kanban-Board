package com.smontiel.kanbanboard;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

/**
 * Created by Salvador Montiel on 9/11/17.
 */
public class TaskItem extends AbstractItem<TaskItem, TaskItem.ViewHolder> {
    private StringHolder title;

    public TaskItem(String title) {
        this.title = new StringHolder(title);
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_taskitem_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.task_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<TaskItem> {
        TextView title;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
        }

        @Override
        public void bindView(TaskItem item, List<Object> payloads) {
            StringHolder.applyTo(item.title, title);
        }

        @Override
        public void unbindView(TaskItem item) {
            title.setText(null);
        }
    }
}
