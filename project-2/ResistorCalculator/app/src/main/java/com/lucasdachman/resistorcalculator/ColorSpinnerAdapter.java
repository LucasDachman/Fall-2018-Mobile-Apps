package com.lucasdachman.resistorcalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ColorSpinnerAdapter extends ArrayAdapter<ResistorColor> {
    public ColorSpinnerAdapter(Context context, ArrayList<ResistorColor> colors) {
        super(context, 0, colors);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = new ImageView(getContext());
        ResistorColor colorItem = getItem(position);
        imageView.setImageDrawable(colorItem.getColorDrawable());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        return imageView;
    }
}
