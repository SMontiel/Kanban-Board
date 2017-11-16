package com.smontiel.kanbanboard.main_view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.smontiel.kanbanboard.R;
import com.smontiel.kanbanboard.data.Column;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.local.LocalDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Salvador Montiel on 8/11/17.
 */
public class MainActivity extends AppCompatActivity {
    @NonNull
    private CompositeDisposable compositeDisposable;
    private Adapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    final DataSource dataSource = LocalDataSource.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("aA", tab.getPosition()+" :Tab " + adapter.getFragmentIdColumn(tab.getPosition()));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("aA", tab.getPosition()+" :UnTab " + adapter.getFragmentIdColumn(tab.getPosition()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("aA", tab.getPosition()+" :ReTab " + adapter.getFragmentIdColumn(tab.getPosition()));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSource.addColumn(new Column("Todo"));
                setupViewPager(viewPager);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());

        compositeDisposable.clear();
        Disposable disposable = dataSource.getColumns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Column>() {
                    @Override
                    public void accept(Column column) throws Exception {
                        TasksFragment cf = TasksFragment.newInstance(column.getId());
                        adapter.addFragment(cf, column.getTitle(), column.getId());
                        TasksPresenter presenter = new TasksPresenter(cf, dataSource);
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("aA", throwable + " : getColumns()");
                    }
                });
        compositeDisposable.add(disposable);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
