package com.smontiel.kanbanboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * Created by Salvador Montiel on 11/11/17.
 */
public class CardsFragment extends Fragment {
    private static String COLUMN_TITLE = "COLUMN_TITLE";
    private RecyclerView recyclerView;
    private ItemAdapter<TaskItem> itemAdapter;

    public static CardsFragment getInstance(String title) {
        Bundle b = new Bundle();
        b.putString(COLUMN_TITLE, title);
        CardsFragment cf = new CardsFragment();
        cf.setArguments(b);
        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_card_list, container, false);
        setupRecyclerView(recyclerView);
        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        itemAdapter = new ItemAdapter<>();
        FastAdapter<TaskItem> fastAdapter = FastAdapter.with(itemAdapter);
        recyclerView.setAdapter(fastAdapter);
        //recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        setItems();
    }

    public void setItems() {
        List<TaskItem> tasks = new ArrayList<>();
        Task t;
        for (int j = 0; j < 10; j++) {
            t = new Task();
            t.setId("id_" + j);
            t.setTitle("Task " + j);
            tasks.add(new TaskItem(t));
        }
        itemAdapter.set(tasks);
    }
}
