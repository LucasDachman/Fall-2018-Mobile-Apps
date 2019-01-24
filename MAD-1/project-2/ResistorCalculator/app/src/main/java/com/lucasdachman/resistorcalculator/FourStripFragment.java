package com.lucasdachman.resistorcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class FourStripFragment extends Fragment {
    int d1, d2;
    double multiplier, tolerance;
    TextView resistanceField, toleranceField, minField, maxField;

    String D1_KEY = "D1";
    String D2_KEY = "D2";
    String MULT_KEY = "MULT";
    String TOL_KEY = "TOL";


    public FourStripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            d1 = savedInstanceState.getInt(D1_KEY);
            d2 = savedInstanceState.getInt(D2_KEY);
            multiplier = savedInstanceState.getDouble(MULT_KEY);
            tolerance = savedInstanceState.getDouble(TOL_KEY);
        }
        return inflater.inflate(R.layout.fragment_four_strip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setSpinnerAdapters();

        resistanceField = getView().findViewById(R.id.resistance_result_4);
        toleranceField = getView().findViewById(R.id.tolerance_result_4);
        minField = getView().findViewById(R.id.min_result_4);
        maxField = getView().findViewById(R.id.max_result_4);
        setResults();
    }

    private void setResults() {
        double resistance = ((d1 * 10) + d2) * multiplier;
        double min = resistance - (resistance * tolerance);
        double max = resistance + (resistance * tolerance);
        setFields(resistance, tolerance, min, max);

    }

    public void setFields(double resistance, double tolerance, double min, double max) {
        Locale locale = getResources().getConfiguration().locale;
        resistanceField.setText(String.format(locale, "%.2fΩ", resistance));
        toleranceField.setText(String.format(locale, "± %.2f%%", tolerance * 100));
        minField.setText(String.format(locale, "%.2fΩ", min));
        maxField.setText(String.format(locale, "%.2fΩ", max));
    }

    private void setSpinnerAdapters() {
        /* Get reference to all 4 spinners */
        Spinner band1 = getView().findViewById(R.id.spinner4_1);
        Spinner band2 = getView().findViewById(R.id.spinner4_2);
        Spinner band3 = getView().findViewById(R.id.spinner4_3);
        Spinner band4 = getView().findViewById(R.id.spinner4_4);

        /* Set color object lists for each spinner */
        band1.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit1Colors()));
        band2.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band3.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geMultiplierColors()));
        band4.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geToleranceColors()));

        /* set onItemSelectedListeners */
        band1.setOnItemSelectedListener(new OnBand1SelectedListener());
        band2.setOnItemSelectedListener(new OnBand2SelectedListener());
        band3.setOnItemSelectedListener(new OnBand3SelectedListener());
        band4.setOnItemSelectedListener(new OnBand4SelectedListener());
    }


    /* OnItemSelected functions for each band */
    private class OnBand1SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            d1 = (int) colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand2SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            d2 = (int) colorItem.getValue();
            setResults();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand3SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            multiplier = colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand4SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            tolerance = colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(D1_KEY, d1);
        outState.putInt(D2_KEY, d2);
        outState.putDouble(MULT_KEY, multiplier);
        outState.putDouble(TOL_KEY, tolerance);

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

}
