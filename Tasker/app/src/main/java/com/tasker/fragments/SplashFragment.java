package com.tasker.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasker.R;
import com.tasker.presenter.SplashFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private SplashFragmentPresenter presenter;


    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment getInstance() {
        Bundle args = new Bundle();
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(args);
        return splashFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        presenter = new SplashFragmentPresenter(this);
        presenter.initSplashTask();
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }


}
