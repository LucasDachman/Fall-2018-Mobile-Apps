package com.lucasdachman.lucas_dachman_final;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EditRestaurantDialogFragment extends DialogFragment {
    private static final String TAG = "EditRestaurantDialog";
    private static final String ARG_NAME = "param1";
    private static final String ARG_URL = "param2";
    private static final String ARG_IDX = "param3";

    private String name;
    private String url;
    private int idx;

    private EditText nameEditText;
    private EditText urlEditText;
    private OnDataChangedListener onDataChangedListener;

    public EditRestaurantDialogFragment() {
        // Required empty public constructor
    }

    public static EditRestaurantDialogFragment newInstance(String name, String url, int idx) {
        EditRestaurantDialogFragment fragment = new EditRestaurantDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_URL, url);
        args.putInt(ARG_IDX, idx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            url = getArguments().getString(ARG_URL);
            idx = getArguments().getInt(ARG_IDX);
        } else {
            name = "";
            url = "";
            idx = -1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_restaurant_dialog, container, false);

        nameEditText = view.findViewById(R.id.name_edit_text);
        urlEditText = view.findViewById(R.id.url_edit_text);

        view.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        view.findViewById(R.id.done_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx == -1) {
                    addRestaurant();
                } else {
                    updateRestaurant();
                }
                dismiss();
            }
        });

        return view;
    }

    private void updateRestaurant() {
        Editable newName = nameEditText.getText();
        Editable newUrl = urlEditText.getText();
        if (newName == null || newUrl == null) {
            return;
        }
        if (newName.toString().isEmpty() || newUrl.toString().isEmpty()) {
            return;
        }
        Log.i(TAG, "Updating Restaurant");
        Restaurant newRestaurant = new Restaurant(newName.toString(), newUrl.toString());
        RestaurantStore.getInstance().restaurants.set(idx, newRestaurant);
        onDataChanged();
    }

    private void addRestaurant() {
        Editable newName = nameEditText.getText();
        Editable newUrl = urlEditText.getText();
        if (newName == null || newUrl == null) {
            return;
        }
        if (newName.toString().isEmpty() || newUrl.toString().isEmpty()) {
            return;
        }
        Log.i(TAG, "Updating Restaurant");
        Restaurant newRestaurant = new Restaurant(newName.toString(), newUrl.toString());
        RestaurantStore.getInstance().restaurants.add(newRestaurant);
        onDataChanged();
    }

    private void onDataChanged() {
        Log.i(TAG, "Attempting to invoke change listener");
        if (onDataChangedListener != null) {
            Log.i(TAG, "Found a changeListener, calling interface method");
            onDataChangedListener.onDataChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            if (idx == -1) {
                dialog.setTitle("New Restaurant");
            } else {
                dialog.setTitle("Edit Restaurant");
                nameEditText.setText(name);
                urlEditText.setText(url);
            }
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.onDataChangedListener = onDataChangedListener;
    }

    public interface OnDataChangedListener {
        void onDataChanged();
    }
}
