package com.smontiel.kanbanboard.main_view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salvador Montiel on 11/11/17.
 */
class Adapter extends FragmentPagerAdapter {
    private final List<Pair<TasksFragment, Long>> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public Adapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(TasksFragment fragment, String title, long idColumn) {
        Pair<TasksFragment, Long> pair = new Pair<>(fragment, idColumn);
        fragments.add(pair);
        fragmentTitles.add(title);
    }

    public long getFragmentIdColumn(int position) {
        return fragments.get(position).second;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).first;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
