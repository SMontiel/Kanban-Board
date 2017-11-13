package com.smontiel.kanbanboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Adapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager, "ready");
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager, String categor) {
        adapter = new Adapter(getSupportFragmentManager());

        for (Pair<String, List<Task>> p : getData()) {
            adapter.addFragment(CardsFragment.getInstance(p.first), p.first);
        }
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);
    }

    private List<Pair<String, List<Task>>> getData() {
        List<Pair<String, List<Task>>> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            List<Task> tasks = new ArrayList<>();
            Task t;
            for (int j = 0; j < 10; j++) {
                t = new Task();
                t.setId("id_" + j);
                t.setTitle("Task " + j);
                tasks.add(t);
            }
            Pair<String, List<Task>> pair = new Pair<>("Column " + (i+1), tasks);
            list.add(pair);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
