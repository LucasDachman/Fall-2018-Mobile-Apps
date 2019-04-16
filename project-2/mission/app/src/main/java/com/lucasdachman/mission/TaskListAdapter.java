package com.lucasdachman.mission;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    private ArrayList<Task> tasks;

    public TaskListAdapter(ArrayList<Task> tasks) {
       super();
       this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // TODO: Get the row view and pass it to the view holder
        return new TaskViewHolder(new TextView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        String taskName = tasks.get(i).getName();
        taskViewHolder.textView.setText(taskName);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public TaskViewHolder(TextView v) {
           super(v);
           this.textView = v;
        }

    }
}
