package com.lucasdachman.mission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private ArrayList<Task> tasks;

    public TaskListAdapter(ArrayList<Task> tasks) {
       super();
       this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        Task task = tasks.get(i);
        taskViewHolder.titleTextView.setText(task.getName());
        taskViewHolder.descriptionTextView.setText(task.getDescription());
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
