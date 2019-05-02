package com.lucasdachman.mission;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditMissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMissionFragment extends DialogFragment implements Button.OnClickListener {
    private static final String ARG_MISSION = "param1";
    private Mission mission;
    private TextInputEditText nameTextView;

    public EditMissionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mission = (Mission) getArguments().getSerializable(ARG_MISSION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_mission, container, false);
        String name = mission != null ? mission.getName() : "";
        nameTextView = view.findViewById(R.id.edit_mission_name_input);
        nameTextView.setText(name != null ? name : "");
        Button cancelButton = view.findViewById(R.id.edit_mission_cancel);
        Button doneButton = view.findViewById(R.id.edit_mission_done);
        cancelButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            if (mission == null) {
                dialog.setTitle("New Mission");
            } else {
                dialog.setTitle("Edit Mission");
            }
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void uploadChange() {
        if (mission == null) {
            uploadNewMission();
        } else {
            updateMission();
        }
    }

    public void updateMission() {
        Editable newName = nameTextView.getText();
        if (newName == null || newName.toString().isEmpty()) {
            return;
        }
        if (newName.toString().equals(mission.getName())) {
            // unchanged text view
            return;
        }
        mission.setName(newName.toString());
        MissionStore.getInstance().updateMission(mission);

    }

    public void uploadNewMission() {
        Editable newName = nameTextView.getText();
        if (newName == null || newName.toString().isEmpty()) {
            return;
        }
        mission = new Mission(newName.toString());
        MissionStore.getInstance().addMission(mission);
    }

    /* Button onClick */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_mission_cancel:
                dismiss();
                break;
            case R.id.edit_mission_done:
                uploadChange();
                dismiss();
        }
    }

    /* Factory method for generating fragments */
    public static EditMissionFragment newInstance(Mission mission) {
        EditMissionFragment fragment = new EditMissionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MISSION, mission);
        fragment.setArguments(args);
        return fragment;
    }
}
