package com.lucasdachman.lab_6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lucasdachman.lab_6.dummy.DummyContent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskFragment.OnListFragmentInteractionListener {

    private TextView mTextMessage;
    private TaskFragment mTaskFragment1;
    private TaskFragment mTaskFragment2;
    private TaskFragment mTaskFragment3;

    private ArrayList<String> taskList1;
    private ArrayList<String> taskList2;
    private ArrayList<String> taskList3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_mission_1:
                    loadFragment(mTaskFragment1);
                    return true;
                case R.id.nav_mission_2:
                    loadFragment(mTaskFragment2);
                    return true;
                case R.id.nav_mission_3:
                    loadFragment(mTaskFragment3);
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

        setUpLists();

        // create fragment
        mTaskFragment1 = TaskFragment.newInstance(taskList1);
        mTaskFragment2 = TaskFragment.newInstance(taskList2);
        mTaskFragment3 = TaskFragment.newInstance(taskList3);

        loadFragment(mTaskFragment1);
    }

    private void setUpLists() {
        taskList1 = new ArrayList<String>();
        taskList2 = new ArrayList<String>();
        taskList3 = new ArrayList<String>();

        taskList1.add("page1-task1");
        taskList1.add("page1-task2");
        taskList1.add("page1-task3");

        taskList2.add("page2-task1");
        taskList2.add("page2-task2");
        taskList2.add("page2-task3");

        taskList3.add("page3-task1");
        taskList3.add("page3-task2");
        taskList3.add("page3-task3");
    }

    private void loadFragment(Fragment fragment){
        if (fragment != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.task_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(String itemName) {
        Log.i("MainActivity", "interaction!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_task_menu_item) {
            Intent intent = new Intent(this, NewTaskActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
