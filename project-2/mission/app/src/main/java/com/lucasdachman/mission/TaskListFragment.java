package com.lucasdachman.mission;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A view that lists tasks for a specific mission
 */
public class TaskListFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_POSITION = "position";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Task> tasks;

    public TaskListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TaskListFragment newInstance(int position) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View for this instance
        View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);

        int position = getArguments().getInt(ARG_POSITION);
        tasks = MissionStore.getInstance().getMissions().get(position).getTasks();

        layoutManager = new LinearLayoutManager(rootView.getContext());
        adapter = new TaskListAdapter(tasks);

        recyclerView = rootView.findViewById(R.id.task_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return rootView;
    }
}

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a TaskListFragment (defined as a static inner class below).
        return TaskListFragment.newInstance(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return MissionStore.getInstance().getMissions().get(position).getName();
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}
