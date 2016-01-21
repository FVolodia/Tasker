package com.tasker.presenter;


import android.support.v4.app.FragmentManager;

import com.tasker.MainActivity;
import com.tasker.R;
import com.tasker.fragments.SplashFragment;
import com.tasker.util.PreferencesManager;

/**
 * Created by FVolodia on 21.01.16.
 */
public class MainActivityPresenter {
    private MainActivity mainActivity;
    private FragmentManager manager;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.manager = mainActivity.getSupportFragmentManager();
    }

    public void runSplash() {
        if (!PreferencesManager.getSplash(mainActivity)) {
            SplashFragment splashFragment = new SplashFragment();
            manager.beginTransaction()
                    .replace(R.id.main_coordinator, splashFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
