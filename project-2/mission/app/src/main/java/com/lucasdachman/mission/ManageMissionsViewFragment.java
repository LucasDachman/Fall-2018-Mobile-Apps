package com.lucasdachman.mission;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A fragment representing a list of Items.
 */

public class ManageMissionsViewFragment extends DialogFragment implements View.OnClickListener, MissionDataChangeListener, SimpleItemTouchHelperCallback.OnStartDragListener {

    private static final String TAG = "ManageMissionsViewFragment";
    private ManageMissionsRecyclerViewAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ManageMissionsViewFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.Theme_Mission_FullScreenDialogStyle);
        adapter = new ManageMissionsRecyclerViewAdapter(getChildFragmentManager(), MissionStore.getInstance().getMissions(), this);
//        MissionStore.getInstance().addMissionDataChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_missions, container, false);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.manage_missions_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Toolbar toolbar = view.findViewById(R.id.manage_missions_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24px);
        toolbar.setNavigationOnClickListener(this);

        FloatingActionButton fab = view.findViewById(R.id.manage_missions_new_button);
        fab.setImageResource(R.drawable.ic_baseline_add_24px);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EditMissionFragment().show(getChildFragmentManager(), TAG);
            }
        });

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

    /* Navigation View.OnClickListener */
    @Override
    public void onClick(View v) {
        dismiss();
    }

    /* MissionDataChangeListener */
    @Override
    public void onDataChange() {
       adapter.notifyDataSetChanged();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
