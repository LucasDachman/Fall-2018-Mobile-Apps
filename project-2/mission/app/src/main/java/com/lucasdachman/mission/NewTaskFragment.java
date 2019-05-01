/*
 * Full screen dialog based on implementation found here: https://medium.com/@haxzie/android-full-screen-dialogs-using-dialogfragment-usage-guide-8fd3cc2cabf8
 */


package com.lucasdachman.mission;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toolbar;
import android.app.DialogFragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.lucasdachman.mission.R;

import java.util.ArrayList;


public class NewTaskFragment extends DialogFragment implements View.OnClickListener, Toolbar.OnMenuItemClickListener {
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
        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24px);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.inflateMenu(R.menu.menu_new_task);

        labelSpinner = view.findViewById(R.id.new_task_label_spinner);
        ArrayAdapter labelSpinnerAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item);
        labelSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        labelSpinnerAdapter.add("Label 1");
        labelSpinnerAdapter.add("Label 2");
        labelSpinner.setAdapter(labelSpinnerAdapter);

        missionSpinner = view.findViewById(R.id.new_task_mission_spinner);
        ArrayAdapter missionSpinnerAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, MissionStore.getInstance().getMissions());
        missionSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        missionSpinner.setAdapter(missionSpinnerAdapter);
        missionSpinner.setSelection(getArguments().getInt(CURRENT_MISSION_INDEX));

        titleEditText = view.findViewById(R.id.new_task_title_input);
        descriptionEditText = view.findViewById(R.id.new_task_description_input);

        return view;
    }

    private void uploadTask() {
        Mission mission = (Mission) missionSpinner.getSelectedItem();
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        if (mission == null) {
            Log.w(TAG, "Warning: attempt to upload null Mission");
            return;
        }
        if ( !title.isEmpty() && !description.isEmpty() ) {
            Log.i(TAG, "Uploading task for mission: " + mission.getName() + " " + mission.getKey());
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

    public static final String CURRENT_MISSION_INDEX = "key idx";

    public static NewTaskFragment newInstance(int missionIndex) {
        NewTaskFragment frag = new NewTaskFragment();
        Bundle args = new Bundle();
        args.putInt(CURRENT_MISSION_INDEX, missionIndex);
        frag.setArguments(args);
        return frag;
    }

}
