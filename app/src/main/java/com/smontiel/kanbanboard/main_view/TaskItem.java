package com.smontiel.kanbanboard.main_view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;
import com.smontiel.kanbanboard.R;
import com.smontiel.kanbanboard.data.Task;

import java.util.List;

/**
 * Created by Salvador Montiel on 9/11/17.
 */
class TaskItem extends AbstractItem<TaskItem, TaskItem.ViewHolder> {
    private StringHolder title;

    public TaskItem(String title) {
        this.title = new StringHolder(title);
    }

    public TaskItem(Task task) {
        this(task.getTitle());
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_taskitem_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.item_card;
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
            title = view.findViewById(R.id.card_description);
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
