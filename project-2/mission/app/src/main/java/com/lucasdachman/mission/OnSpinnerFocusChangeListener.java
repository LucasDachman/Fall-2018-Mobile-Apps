package com.lucasdachman.mission;

import android.util.Log;
import android.view.View;
import android.widget.Spinner;

public class OnSpinnerFocusChangeListener implements View.OnFocusChangeListener {
    public static final String TAG = "OnSpinnerFocusChange";
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.i(TAG, Boolean.toString(hasFocus));
        if (hasFocus) {
            v.setBackground(v.getContext().getDrawable(R.drawable.spinner_border_selected));
        } else {
            v.setBackground(v.getContext().getDrawable(R.drawable.spinner_border));
        }
    }
}
