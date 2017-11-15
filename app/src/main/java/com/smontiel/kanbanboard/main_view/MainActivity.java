package com.smontiel.kanbanboard.main_view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.smontiel.kanbanboard.R;
import com.smontiel.kanbanboard.data.Column;
import com.smontiel.kanbanboard.data.DataSource;
import com.smontiel.kanbanboard.data.fake.FakeDataSource;

/**
 * Created by Salvador Montiel on 8/11/17.
 */
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
            setupViewPager(viewPager);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());
        DataSource repo = FakeDataSource.getInstance();

        for (Column c : repo.getColumns()) {
            TasksFragment cf = TasksFragment.newInstance(c.getId());
            adapter.addFragment(cf, c.getTitle());
            TasksPresenter presenter = new TasksPresenter(cf, FakeDataSource.getInstance());
        }
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
