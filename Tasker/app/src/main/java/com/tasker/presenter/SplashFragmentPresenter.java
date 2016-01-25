package com.tasker.presenter;

import android.os.AsyncTask;

import com.tasker.fragments.SplashFragment;

import java.util.concurrent.TimeUnit;

/**
 * Created by FVolodia on 22.01.16.
 */
public class SplashFragmentPresenter {

    private SplashFragment splashFragment;

    public SplashFragmentPresenter(SplashFragment splashFragment) {
        this.splashFragment = splashFragment;
    }

    class SplashTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (splashFragment.getActivity() != null) {
                splashFragment.getActivity().getSupportFragmentManager().popBackStack();
            }
            return null;
        }

    }

    public SplashTask initSplashTask() {
        SplashTask splashTask = new SplashTask();
        splashTask.execute();
        return splashTask;
    }
}
