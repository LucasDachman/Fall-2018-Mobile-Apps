package com.lucasdachman.resistorcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class FiveStripFragment extends Fragment {
    int d1, d2, d3;
    double multiplier, tolerance;
    ResultFragment resultFragment;

    public FiveStripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five_strip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("AH", getChildFragmentManager().getFragments().toString());
        resultFragment = (ResultFragment) getChildFragmentManager().getFragments().get(0);
        setSpinnerAdapters();
    }

    private void setResults() {
        if (resultFragment != null) {
            double resistance = ((d1 * 100) + (d2 * 10) + d3) * multiplier;
            double min = resistance - (resistance * tolerance);
            double max = resistance + (resistance * tolerance);
            resultFragment.setFields(resistance, tolerance, min, max);
            return;
        }

    }

    private void setSpinnerAdapters() {
        /* Get reference to all 4 spinners */
        Spinner band1 = getView().findViewById(R.id.spinner5_1);
        Spinner band2 = getView().findViewById(R.id.spinner5_2);
        Spinner band3 = getView().findViewById(R.id.spinner5_3);
        Spinner band4 = getView().findViewById(R.id.spinner5_4);
        Spinner band5 = getView().findViewById(R.id.spinner5_5);

        /* Set color object lists for each spinner */
        band1.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit1Colors()));
        band2.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band3.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.getDigit23Colors()));
        band4.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geMultiplierColors()));
        band5.setAdapter(new ColorSpinnerAdapter(getContext(), ResistorColor.geToleranceColors()));

        /* set onItemSelectedListeners */
        band1.setOnItemSelectedListener(new OnBand1SelectedListener());
        band2.setOnItemSelectedListener(new OnBand2SelectedListener());
        band3.setOnItemSelectedListener(new OnBand3SelectedListener());
        band4.setOnItemSelectedListener(new OnBand4SelectedListener());
        band5.setOnItemSelectedListener(new OnBand5SelectedListener());
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

}
