package com.tasker.activity;

import android.app.DialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tasker.R;
import com.tasker.adapter.TabsPageFragmentAdapter;
import com.tasker.database.DBHelper;
import com.tasker.dialog.AddingTaskDialogFragment;
import com.tasker.fragments.CurrentTaskFragment;
import com.tasker.fragments.DoneTaskFragment;
import com.tasker.fragments.TaskFragment;
import com.tasker.model.ModelTask;
import com.tasker.presenter.MainActivityPresenter;
import com.tasker.util.Alert;
import com.tasker.util.PreferencesManager;

public class MainActivity extends AppCompatActivity implements AddingTaskDialogFragment.AddingTaskListener, CurrentTaskFragment.OnTaskDoneListener, DoneTaskFragment.OnRestoreTaskListener {
    private MainActivityPresenter presenter;
    private CoordinatorLayout coordinatorLayout;
    private TabsPageFragmentAdapter adapter;
    private TaskFragment currentTaskFragment;
    private TaskFragment doneTaskFragment;
    public DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenter.runSplash();
        setUI();
        dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_splash);
        menuItem.setChecked(PreferencesManager.getSplash(this));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_splash) {
            item.setChecked(!item.isChecked());
            PreferencesManager.putSplash(this, item.isChecked());
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void setUI() {

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.background_white));
            setSupportActionBar(toolbar);
        }

        adapter = new TabsPageFragmentAdapter(getSupportFragmentManager(), 2, this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addingTaskDialogFragment = new AddingTaskDialogFragment();
                addingTaskDialogFragment.show(getFragmentManager(), "AddingTaskDialogFragment");
            }
        });

        currentTaskFragment = (CurrentTaskFragment) adapter.getItem(TabsPageFragmentAdapter.CURRENT_TASK_FRAGMENT_POSITION);
        doneTaskFragment = (DoneTaskFragment) adapter.getItem(TabsPageFragmentAdapter.DONE_TASK_FRAGMENT_POSITION);


    }

    @Override
    public void onTaskAdded(ModelTask newTask) {
        currentTaskFragment.addTask(newTask, true);
//        Alert.snackBar(coordinatorLayout, "Task Added");

    }

    @Override
    public void onTaskAddingCanceled() {
//        Alert.snackBar(coordinatorLayout, "Task Canceled");

    }

    @Override
    public void onRestore(ModelTask task) {
        currentTaskFragment.addTask(task,false);
    }

    @Override
    public void onTaskDone(ModelTask task) {
        doneTaskFragment.addTask(task, false);

    }
}
