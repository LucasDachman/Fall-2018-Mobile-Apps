package com.lucasdachman.lab7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ColorItemAdapter(this, getColors()));
    }

    private ArrayList<Integer> getColors() {
        return new ArrayList<Integer>() {
            {
                add(Color.RED);
                add(Color.BLUE);
                add(Color.GREEN);
                add(Color.YELLOW);
            }
        };
    }
}
