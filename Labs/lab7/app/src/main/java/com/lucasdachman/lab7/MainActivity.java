package com.lucasdachman.lab7;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ColorInfo> colors = ColorInfo.generateColors();
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Spinner **/
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ColorItemAdapter(this, colors));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPosition = -1;
            }
        });

        /* Button **/
        Button button = findViewById(R.id.goButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    ColorInfo color = colors.get(selectedPosition);
                    Intent intent = new Intent(getApplicationContext(), ColorInfoActivity.class);
                    intent.putExtra("name", color.getName());
                    intent.putExtra("url", color.getUrl());
                    startActivity(intent);
                }
            }
        });

    }
}
