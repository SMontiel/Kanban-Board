package com.smontiel.kanbanboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salvador Montiel on 9/11/17.
 */
public class ColumnItem extends AbstractItem<ColumnItem, ColumnItem.ViewHolder> {
    private List<TaskItem> tasks;

    public ColumnItem() {
        this.tasks = getItems();
    }

    private List<TaskItem> getItems() {
        List<TaskItem> list = new ArrayList<>();
        TaskItem ti;
        for (int i = 0; i < 10; i++) {
            ti = new TaskItem("Task " + i);
            list.add(ti);
        }
        return list;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return R.id.fastadapter_columnitem_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.column_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<ColumnItem> {
        RecyclerView tasksRecyclerView;
        RecyclerView.LayoutManager layoutManager;
        private ItemAdapter<TaskItem> itemAdapter;

        public ViewHolder(View view) {
            super(view);
            tasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
            initRecyclerView();
        }

        @Override
        public void bindView(ColumnItem item, List<Object> payloads) {
            itemAdapter.add(item.tasks);
        }

        private void initRecyclerView() {
            tasksRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this.itemView.getContext());
            tasksRecyclerView.setLayoutManager(layoutManager);
            itemAdapter = new ItemAdapter<>();
            FastAdapter<TaskItem> fastAdapter = FastAdapter.with(itemAdapter);

            tasksRecyclerView.setAdapter(fastAdapter);


        }

        @Override
        public void unbindView(ColumnItem item) {
            itemAdapter.clear();
        }
    }
}