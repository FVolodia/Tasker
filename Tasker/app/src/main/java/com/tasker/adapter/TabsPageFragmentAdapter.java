package com.tasker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tasker.R;
import com.tasker.fragments.CurrentTaskFragment;
import com.tasker.fragments.DoneTaskFragment;

/**
 * Created by FVolodia on 22.01.16.
 */
public class TabsPageFragmentAdapter extends FragmentPagerAdapter {

    private int numberOfTab;
    private Context context;

    public TabsPageFragmentAdapter(FragmentManager fm, int numberOfTab, Context context) {
        super(fm);
        this.numberOfTab = numberOfTab;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CurrentTaskFragment.getInstance();
            case 1:
                return DoneTaskFragment.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.current_task);
            case 1:
                return context.getResources().getString(R.string.done_task);
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }
}
