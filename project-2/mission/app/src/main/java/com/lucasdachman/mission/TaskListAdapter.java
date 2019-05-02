package com.lucasdachman.mission;

import android.annotation.SuppressLint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {
    public static final String TAG = "TaskListAdapter";
    private ArrayList<Task> tasks;
    private Mission mission;
    private FragmentManager fragmentManager;
    private SimpleItemTouchHelperCallback.OnStartDragListener dragListener;

    public TaskListAdapter(FragmentManager fragmentManager, Mission mission, SimpleItemTouchHelperCallback.OnStartDragListener dragListener) {
        super();
        this.tasks = mission.getTasksAsList();
        this.mission = mission;
        this.fragmentManager = fragmentManager;
        this.dragListener = dragListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder taskViewHolder, int i) {
        final Task task = tasks.get(i);
        taskViewHolder.titleTextView.setText(task.getName());
        taskViewHolder.descriptionTextView.setText(task.getDescription());
        taskViewHolder.checkBox.setChecked(task.getChecked());
        taskViewHolder.task = task;

        taskViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setChecked(isChecked);
                MissionStore.getInstance().updateTask(mission, task);
            }
        });

        taskViewHolder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.isFromSource(event, MotionEvent.ACTION_DOWN)) {
                    dragListener.onStartDrag(taskViewHolder);
                }
                return false;
            }
        });

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
                menu.add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        EditTaskFragment frag = EditTaskFragment.newEditInstance(mission, task);
                        frag.show(fragmentManager, TAG);
                        return false;
                    }
                });
            }
        });

        taskViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTaskFragment frag = EditTaskFragment.newEditInstance(mission, task);
                frag.show(fragmentManager, TAG);
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

    /* ItemTouchHelperAdapter */
    @Override
    public void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Task from = ((TaskViewHolder) viewHolder).task;
        Task to = ((TaskViewHolder) target).task;
        float fromOrder = from.getOrder();
        from.setOrder(to.getOrder());
        to.setOrder(fromOrder);
        MissionStore.getInstance().updateTask(mission, from);
        MissionStore.getInstance().updateTask(mission, to);
    }

    /*
     * View Holder Class
     */

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView handle;
        CheckBox checkBox;
        Task task;

        public TaskViewHolder(final View v) {
            super(v);
            this.titleTextView = v.findViewById(R.id.task_item_title);
            this.descriptionTextView = v.findViewById(R.id.task_item_description);
            this.checkBox = v.findViewById(R.id.checkBox);
            this.handle = v.findViewById(R.id.handle);
        }
    }
}
