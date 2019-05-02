/*
 * Full screen dialog based on implementation found here: https://medium.com/@haxzie/android-full-screen-dialogs-using-dialogfragment-usage-guide-8fd3cc2cabf8
 */


package com.lucasdachman.mission;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

public class EditTaskFragment extends DialogFragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
    public static final String TAG = "EditTaskFragment";

    // UI Elements
    Spinner missionSpinner;
    TextInputEditText titleEditText;
    TextInputEditText descriptionEditText;

    // For editing a task
    boolean isNewTask;
    Mission mission;
    Task task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.Theme_Mission_FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);

        mission = (Mission) getArguments().getSerializable(CURRENT_MISSION);

        Toolbar toolbar = view.findViewById(R.id.new_task_toolbar);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24px);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.inflateMenu(R.menu.menu_new_task);

        missionSpinner = view.findViewById(R.id.new_task_mission_spinner);
        ArrayAdapter missionSpinnerAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, MissionStore.getInstance().getMissions());
        missionSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        missionSpinner.setAdapter(missionSpinnerAdapter);
        int missionIndex = MissionStore.getInstance().getMissions().indexOf(mission);
        missionSpinner.setSelection(missionIndex);

        titleEditText = view.findViewById(R.id.new_task_title_input);
        descriptionEditText = view.findViewById(R.id.new_task_description_input);

        isNewTask = getArguments().getBoolean(IS_NEW);
        // if we're editing a task rather than creating a new one
        if (isNewTask) {
            Log.i(TAG, "Building new task");
            toolbar.setTitle("New Task");
        } else {
            task = (Task) getArguments().getSerializable(CURRENT_TASK);
            Log.i(TAG, "Editing task: " + task.getName());
            titleEditText.setText(task.getName());
            descriptionEditText.setText(task.getDescription());
            toolbar.setTitle("Edit Task");
        }

        return view;
    }

    private void uploadTask() {
        Mission selectedMission = (Mission) missionSpinner.getSelectedItem();
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        if (selectedMission == null) {
            Log.w(TAG, "Warning: attempt to save ");
            return;
        }
        if (title.isEmpty() || description.isEmpty()) {
            Log.w(TAG, "Warning: attempt to save task with empty fields");
            return;
        }
        if (isNewTask) {
            Task task = new Task(title, description);
            MissionStore.getInstance().addTask(mission, task);
        } else {
            // editing a task
            task.setName(title);
            task.setDescription(description);
            if (mission.getKey() != selectedMission.getKey()) {
                // if the selected mission was changed, need to delete task and re-add
                MissionStore.getInstance().deleteTask(mission, task);
                MissionStore.getInstance().addTask(selectedMission, task);
            } else {
                // mission was not changed, just update the task
                MissionStore.getInstance().updateTask(mission, task);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    /* Navigation View.OnClickListener */
    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.new_task_save_item) {
            uploadTask();
            dismiss();
        }
        return true;
    }

    /* static methods */

    public static final String CURRENT_MISSION = "curr mission";
    public static final String CURRENT_TASK = "curr task";
    public static final String IS_NEW = "is new";

    public static EditTaskFragment newInstance(Mission mission) {
        EditTaskFragment frag = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_MISSION, mission);
        args.putBoolean(IS_NEW, true);
        frag.setArguments(args);
        return frag;
    }

    public static EditTaskFragment newEditInstance(Mission mission, Task task) {
        EditTaskFragment frag = new EditTaskFragment();
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_MISSION, mission);
        args.putSerializable(CURRENT_TASK, task);
        args.putBoolean(IS_NEW, false);
        frag.setArguments(args);
        return frag;
    }
}
