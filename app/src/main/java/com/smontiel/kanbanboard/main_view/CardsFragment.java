package com.smontiel.kanbanboard.main_view;

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
import com.smontiel.kanbanboard.R;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.Task;
import com.smontiel.kanbanboard.data.fake.FakeDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salvador Montiel on 11/11/17.
 */
public class CardsFragment extends Fragment implements CardsContract.View {
    private CardsContract.Presenter presenter;

    private static String COLUMN_ID = "COLUMN_ID";
    private RecyclerView recyclerView;
    private ItemAdapter<TaskItem> itemAdapter;

    private int idColumn;

    public static CardsFragment newInstance(int idColumn) {
        Bundle b = new Bundle();
        b.putInt(COLUMN_ID, idColumn);
        CardsFragment cf = new CardsFragment();
        cf.setArguments(b);
        return cf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        idColumn = getArguments().getInt(COLUMN_ID);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
        presenter.loadTasks(idColumn);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void setPresenter(CardsContract.Presenter presenter) {
        //TODO: check not null
        this.presenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<TaskItem> tasks) {
        itemAdapter.set(tasks);
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
