package com.lucasdachman.mission;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;
import android.app.DialogFragment;

public class NewTaskFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "NewTaskDialog";

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

        Spinner labelSpinner = view.findViewById(R.id.new_task_label_spinner);
        ArrayAdapter labelSpinnerAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.labels, R.layout.support_simple_spinner_dropdown_item);
        labelSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        labelSpinner.setAdapter(labelSpinnerAdapter);

        Spinner missionSpinner = view.findViewById(R.id.new_task_mission_spinner);
        ArrayAdapter missionSpinnerAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item);
        missionSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        missionSpinnerAdapter.add("Mission 1");
        missionSpinnerAdapter.add("Mission 2");
        missionSpinner.setAdapter(missionSpinnerAdapter);

        return view;
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
