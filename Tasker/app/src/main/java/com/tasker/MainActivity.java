package com.tasker;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tasker.adapter.TabsPageFragmentAdapter;
import com.tasker.presenter.MainActivityPresenter;
import com.tasker.util.PreferencesManager;

public class MainActivity extends AppCompatActivity {
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenter.runSplash();
        setUI();
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        TabsPageFragmentAdapter adapter = new TabsPageFragmentAdapter(getSupportFragmentManager(), 2, this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.setupWithViewPager(viewPager);


    }

}
