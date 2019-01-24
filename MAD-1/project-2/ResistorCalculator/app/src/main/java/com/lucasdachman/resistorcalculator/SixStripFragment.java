package com.lucasdachman.resistorcalculator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class SixStripFragment extends Fragment {
    int d1, d2, d3, tempco;
    double multiplier, tolerance;
    TextView resistanceField, toleranceField, minField, maxField, tempField;


    public SixStripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_six_strip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setSpinnerAdapters();

        resistanceField = getView().findViewById(R.id.resistance_result_6);
        toleranceField = getView().findViewById(R.id.tolerance_result_6);
        minField = getView().findViewById(R.id.min_result_6);
        maxField = getView().findViewById(R.id.max_result_6);
        tempField = getView().findViewById(R.id.temp_result_6);
    }


    private void setResults() {
        double resistance = ((d1 * 100) + (d2 * 10) + d3) * multiplier;
        double min = resistance - (resistance * tolerance);
        double max = resistance + (resistance * tolerance);
        setFields(resistance, tolerance, min, max, tempco);

    }

    public void setFields(double resistance, double tolerance, double min, double max, int tempco) {
        Locale locale = getResources().getConfiguration().locale;
        resistanceField.setText(String.format(locale, "%.2fΩ", resistance));
        toleranceField.setText(String.format(locale, "± %.2f%%", tolerance * 100));
        minField.setText(String.format(locale, "%.2fΩ", min));
        maxField.setText(String.format(locale, "%.2fΩ", max));
        tempField.setText(tempco + "ppm/°C");
    }

    private void setSpinnerAdapters() {
        /* Get reference to all 4 spinners */
        Spinner band1 = getView().findViewById(R.id.spinner6_1);
        Spinner band2 = getView().findViewById(R.id.spinner6_2);
        Spinner band3 = getView().findViewById(R.id.spinner6_3);
        Spinner band4 = getView().findViewById(R.id.spinner6_4);
        Spinner band5 = getView().findViewById(R.id.spinner6_5);
        Spinner band6 = getView().findViewById(R.id.spinner6_6);

        /* Set color object lists for each spinner */
        band1.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit1Colors()));
        band2.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band3.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band4.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geMultiplierColors()));
        band5.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geToleranceColors()));
        band6.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getTempcoColors()));

        /* set onItemSelectedListeners */
        band1.setOnItemSelectedListener(new OnBand1SelectedListener());
        band2.setOnItemSelectedListener(new OnBand2SelectedListener());
        band3.setOnItemSelectedListener(new OnBand3SelectedListener());
        band4.setOnItemSelectedListener(new OnBand4SelectedListener());
        band5.setOnItemSelectedListener(new OnBand5SelectedListener());
        band6.setOnItemSelectedListener(new OnBand6SelectedListener());
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
            d3 = (int) colorItem.getValue();
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
            multiplier = colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnBand5SelectedListener implements AdapterView.OnItemSelectedListener {
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

    private class OnBand6SelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ResistorColor colorItem = (ResistorColor) parent.getItemAtPosition(position);
            tempco = (int) colorItem.getValue();
            setResults();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
