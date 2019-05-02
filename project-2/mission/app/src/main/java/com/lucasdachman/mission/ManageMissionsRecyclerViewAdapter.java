package com.lucasdachman.mission;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ManageMissionsRecyclerViewAdapter extends RecyclerView.Adapter<ManageMissionsRecyclerViewAdapter.ViewHolder> {

    private final List<Mission> missionList;
    private final FragmentManager fragmentManager;
    private final String TAG = "ManageMissionsRecyclerViewAdapter";

    public ManageMissionsRecyclerViewAdapter(FragmentManager fm, List<Mission> missions) {
        missionList = missions;
        this.fragmentManager = fm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_manage_missions_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Mission mission = missionList.get(position);
        holder.mission = mission;
        final String name = mission.getName();
        holder.titleView.setText(name);
        holder.numberView.setText(String.valueOf(position + 1));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditMissionFragment editMissionFragment = EditMissionFragment.newInstance(holder.mission);
                editMissionFragment.show(fragmentManager, TAG);
            }
        });

        holder.mView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        MissionStore.getInstance().deleteMission(mission);
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return missionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleView;
        public final TextView numberView;
        public Mission mission;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = view.findViewById(R.id.mission_item_title);
            numberView = view.findViewById(R.id.mission_item_number);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
