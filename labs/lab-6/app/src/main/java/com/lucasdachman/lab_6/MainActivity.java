package com.lucasdachman.lab_6;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.lucasdachman.lab_6.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;
    private TaskFragment mTaskFragment1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_mission_1:
                    loadFragment(mTaskFragment1);
                    return true;
                case R.id.nav_mission_2:
                    // implement fragment load
                    return true;
                case R.id.nav_mission_3:
                    // implement fragment load
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // create fragment
        mTaskFragment1 = TaskFragment.newInstance();
    }

    private void loadFragment(Fragment fragment){
        if (fragment != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.task_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i("MainActivity", "interaction!");
    }
}
