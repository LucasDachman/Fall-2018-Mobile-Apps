package com.lucasdachman.mission;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private ArrayList<Task> tasks;
    private Mission mission;

    public TaskListAdapter(Mission mission) {
        super();
        this.tasks = mission.getTasksAsList();
        this.mission = mission;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        final Task task = tasks.get(i);
        taskViewHolder.titleTextView.setText(task.getName());
        taskViewHolder.descriptionTextView.setText(task.getDescription());
        taskViewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        MissionStore.getInstance().deleteTask(mission, task);
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.task_list_item;
    }

    /*
     * View Holder Class
     */

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        public TaskViewHolder(final View v) {
            super(v);
            this.titleTextView = v.findViewById(R.id.task_item_title);
            this.descriptionTextView = v.findViewById(R.id.task_item_description);
        }
    }
}
