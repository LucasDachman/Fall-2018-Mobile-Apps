package com.lucasdachman.resistorcalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;


public class ResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String RES_PARAM = "resistance";
    private static final String TOL_PARAM = "tolerance";
    private static final String MIN_PARAM = "min";
    private static final String MAX_PARAM = "max";

    // passed values
    private double resistance;
    private double tolerance;
    private double min;
    private double max;

    // UI components
    TextView resistanceResult;
    TextView toleranceResult;
    TextView minResult;
    TextView maxResult;


    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(double resistance, double tolerance, double min, double max) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putDouble(RES_PARAM, resistance);
        args.putDouble(TOL_PARAM, tolerance);
        args.putDouble(MIN_PARAM, min);
        args.putDouble(MAX_PARAM, max);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resistance = getArguments().getDouble(RES_PARAM);
            tolerance = getArguments().getDouble(TOL_PARAM);
            min = getArguments().getDouble(MIN_PARAM);
            max = getArguments().getDouble(MAX_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = getActivity();
        resistanceResult = activity.findViewById(R.id.resistance_result);
        toleranceResult = activity.findViewById(R.id.tolerance_result);
        minResult = activity.findViewById(R.id.min_result);
        maxResult = activity.findViewById(R.id.max_result);

        // set values
        setFields(resistance, tolerance, min, max);
    }

    public void setFields(double resistance, double tolerance, double min, double max) {
        Locale locale = getResources().getConfiguration().locale;
        resistanceResult.setText(String.format(locale, "%.2fΩ", resistance));
        toleranceResult.setText(String.format(locale, "± %.2f%%", tolerance * 100));
        minResult.setText(String.format(locale, "%.2fΩ", min));
        maxResult.setText(String.format(locale, "%.2fΩ", max));
    }
}
