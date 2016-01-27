package com.tasker.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.tasker.adapter.CurrentTaskAdapter;
import com.tasker.adapter.TaskAdapter;
import com.tasker.model.ModelTask;

/**
 * Created by FVolodia on 27.01.16.
 */
public abstract class TaskFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected TaskAdapter adapter;

    public void addTask(ModelTask newTask) {
        int position = -1;

        for (int i = 0; i < adapter.getItemCount(); i++) {
            if (adapter.getItems(i).isTask()) {
                ModelTask task = (ModelTask) adapter.getItems(i);
                if (newTask.getDate() < task.getDate()) {
                    position = i;
                    break;
                }
            }
        }

        if (position != -1) {
            adapter.addItem(position, newTask);
        } else adapter.addItem(newTask);
    }

    public abstract void moveTask(ModelTask task);
}
