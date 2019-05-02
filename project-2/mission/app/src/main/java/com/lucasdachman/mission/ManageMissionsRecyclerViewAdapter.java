package com.lucasdachman.mission;

import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageMissionsRecyclerViewAdapter extends RecyclerView.Adapter<ManageMissionsRecyclerViewAdapter.ViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    private final List<Mission> missionList;
    private final FragmentManager fragmentManager;
    private SimpleItemTouchHelperCallback.OnStartDragListener dragListener;
    private final String TAG = "ManageMissionsRecyclerViewAdapter";

    public ManageMissionsRecyclerViewAdapter(FragmentManager fm, List<Mission> missions, SimpleItemTouchHelperCallback.OnStartDragListener dragListener) {
        missionList = missions;
        this.fragmentManager = fm;
        this.dragListener = dragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_manage_missions_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Mission mission = missionList.get(position);
        holder.mission = mission;
        final String name = mission.getName();
        holder.titleView.setText(name);
        holder.numberView.setText(String.valueOf(position + 1));
        holder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.isFromSource(event, MotionEvent.ACTION_DOWN)) {
                    dragListener.onStartDrag(holder);
                }
                return true;
            }
        });

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

    /* ItemTouchHelperAdapter */
    @Override
    public void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Mission from = ((ViewHolder) viewHolder).mission;
        Mission to = ((ViewHolder) target).mission;

        // swap in list
        int fromIdx = missionList.indexOf(from);
        int toIdx = missionList.indexOf(to);
        if (fromIdx < 0 || toIdx < 0)  {
            notifyDataSetChanged();
            return;
        }
        Collections.swap(missionList, fromIdx, toIdx);

        // swap in firebase
        float fromOrder = from.getOrder();
        from.setOrder(to.getOrder());
        to.setOrder(fromOrder);
        MissionStore.getInstance().updateMission(from);
        MissionStore.getInstance().updateMission(to);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView titleView;
        public final TextView numberView;
        public ImageView handle;
        public Mission mission;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = view.findViewById(R.id.mission_item_title);
            numberView = view.findViewById(R.id.mission_item_number);
            handle = view.findViewById(R.id.handle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titleView.getText() + "'";
        }
    }
}
