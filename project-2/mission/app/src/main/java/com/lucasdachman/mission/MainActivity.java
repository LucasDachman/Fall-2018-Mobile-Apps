package com.lucasdachman.mission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements MissionDataChangeListener, Toolbar.OnMenuItemClickListener {

    private final String TAG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        MissionStore.getInstance().addMissionDataChangeListener(this);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager, true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        fab.setImageResource(R.drawable.ic_baseline_add_24px);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mission currentMission = MissionStore.getInstance().getMissions().get(mViewPager.getCurrentItem());
                EditTaskFragment editTaskFragment = EditTaskFragment.newInstance(currentMission);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                editTaskFragment.show(ft, EditTaskFragment.TAG);
                ft.commitNow();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Interface method for MissionDataChanged
    @Override
    public void onDataChange() {
        Log.i(TAG, "onDataChange");
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    /* Toolbar menu item */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ManageMissionsViewFragment manageMissionsViewFragment = new ManageMissionsViewFragment();
        manageMissionsViewFragment.show(getSupportFragmentManager(), TAG);
        return true;
    }
}
