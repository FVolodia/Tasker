package com.tasker.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tasker.R;
import com.tasker.adapter.DoneTaskAdapter;
import com.tasker.database.DBHelper;
import com.tasker.model.ModelTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends TaskFragment {

    private OnRestoreTaskListener onRestoreTaskListener;

    public DoneTaskFragment() {
        // Required empty public constructor
    }

    public interface OnRestoreTaskListener {
        void onRestore(ModelTask task);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onRestoreTaskListener = (OnRestoreTaskListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implemented OnRestoreTaskListener");
        }
    }

    public static DoneTaskFragment getInstance() {
        Bundle args = new Bundle();
        DoneTaskFragment doneTaskFragment = new DoneTaskFragment();
        doneTaskFragment.setArguments(args);
        return doneTaskFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done_task, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvDoneTask);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DoneTaskAdapter(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void addTaskFromDB() {
        List<ModelTask> tasks = new ArrayList<>();
        tasks.addAll(activity.dbHelper.query().getTask(DBHelper.SELECTION_STATUS, new String[]{Integer.toString(ModelTask.STATUS_DONE)}, DBHelper.TASK_DATE_COLUMN));
        for (int i = 0; i < tasks.size(); i++) {
            addTask(tasks.get(i), false);
        }

    }

    @Override
    public void moveTask(ModelTask task) {
        onRestoreTaskListener.onRestore(task);
    }
}
