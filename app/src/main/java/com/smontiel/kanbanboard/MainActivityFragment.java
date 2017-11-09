package com.smontiel.kanbanboard;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private RecyclerView columnsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        this.columnsRecyclerView = rootView.findViewById(R.id.columns_recycler_view);
        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {
        columnsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        columnsRecyclerView.setLayoutManager(layoutManager);
        ItemAdapter<ColumnItem> itemAdapter = new ItemAdapter<>();
        FastAdapter<ColumnItem> fastAdapter = FastAdapter.with(itemAdapter);

        columnsRecyclerView.setAdapter(fastAdapter);

        itemAdapter.add(getItems());
    }

    private List<ColumnItem> getItems() {
        List<ColumnItem> list = new ArrayList<>();
        ColumnItem ti;
        for (int i = 0; i < 5; i++) {
            ti = new ColumnItem();
            list.add(ti);
        }
        return list;
    }

//    private List<TaskItem> getItems() {
//        List<TaskItem> list = new ArrayList<>();
//        TaskItem ti;
//        for (int i = 0; i < 10; i++) {
//            ti = new TaskItem("Task " + i);
//            list.add(ti);
//        }
//        return list;
//    }
}
