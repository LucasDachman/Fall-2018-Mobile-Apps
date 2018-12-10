package com.lucasdachman.resistorcalculator;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        ResistorColor colorItem = getItem(position);
        TextView tv = new TextView(getContext());
        tv.setText(colorItem.getName());
        tv.setTextAppearance(getContext(), R.style.spinner_item);
        tv.setBackgroundColor(colorItem.getColor());
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        tv.setLayoutParams(new ViewGroup.LayoutParams(width, 100));

        return tv;
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView = new ImageView(getContext());
        ResistorColor colorItem = getItem(position);
        imageView.setImageDrawable(colorItem.getColorDrawable());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        return imageView;
    }
}
