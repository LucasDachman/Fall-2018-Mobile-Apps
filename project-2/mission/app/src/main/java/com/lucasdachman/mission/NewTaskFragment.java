package com.lucasdachman.mission;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;
import android.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NewTaskFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "NewTaskDialog";

    // UI Elements
    Spinner labelSpinner;
    Spinner missionSpinner;
    TextInputEditText titleEditText;
    TextInputEditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.Theme_Mission_FullScreenDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_new_task, container, false);
        Toolbar toolbar = view.findViewById(R.id.new_task_toolbar);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle("New Task");
        toolbar.setTitleTextColor(Color.WHITE);

        labelSpinner = view.findViewById(R.id.new_task_label_spinner);
        ArrayAdapter labelSpinnerAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item);
        labelSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        labelSpinnerAdapter.add("Label 1");
        labelSpinnerAdapter.add("Label 2");
        labelSpinner.setAdapter(labelSpinnerAdapter);

        missionSpinner = view.findViewById(R.id.new_task_mission_spinner);
        ArrayAdapter missionSpinnerAdapter = new MissionArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, MissionStore.getInstance().getMissions());
        missionSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        missionSpinner.setAdapter(missionSpinnerAdapter);

        titleEditText = view.findViewById(R.id.new_task_title_input);
        descriptionEditText = view.findViewById(R.id.new_task_description_input);

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        Mission mission = (Mission) missionSpinner.getSelectedItem();
        Log.i(TAG, "Mission: " + mission.getName() + " " + mission.getKey());
        if ( !title.isEmpty() && !description.isEmpty() ) {
            Task task = new Task(title, description);
            MissionStore.getInstance().addTask(mission, task);
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

    /* View.OnClickListener */
    @Override
    public void onClick(View v) {
        dismiss();
    }

    /* static methods */

    public static NewTaskFragment newInstance() {
        return new NewTaskFragment();
    }
}
