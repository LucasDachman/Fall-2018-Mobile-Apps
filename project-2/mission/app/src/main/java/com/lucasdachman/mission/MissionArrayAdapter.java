package com.lucasdachman.mission;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MissionArrayAdapter extends ArrayAdapter<Mission> {

    public MissionArrayAdapter(@NonNull Context context, int resource, @NonNull List<Mission> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView;
        if (convertView != null) {
            textView = (TextView) convertView;
        } else {
           textView = new TextView(parent.getContext());
        }
        textView.setText(getItem(position).getName());
        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return this.getView(position, convertView, parent);
    }
}
