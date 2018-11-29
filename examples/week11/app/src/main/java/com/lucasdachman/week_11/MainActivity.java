package com.lucasdachman.week_11;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findSport(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int costId = radioGroup.getCheckedRadioButtonId();
        ToggleButton togButton = findViewById(R.id.toggleButton);
        boolean location = togButton.isChecked();
        Spinner spinner = findViewById(R.id.spinner);
        String selectedType = String.valueOf(spinner.getSelectedItem());
        String perfectSport = "";
        if (costId == -1) {
            Context context =getApplicationContext();
            CharSequence text = "Please Select a Cost Level";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (location) { // inside
                if (costId == R.id.radio1) {
                   perfectSport = "A Home Workout";
                } else {
                    switch (selectedType) {
                        case "cardio":
                            perfectSport = "Spin Class";
                            break;
                        case "strength":
                            perfectSport = "Weight Training";
                            break;
                        case "flexibility":
                            perfectSport = "Yoga";
                            break;
                        default:
                            perfectSport = "Yoga";

                    }
                }
            } else { // outside
                switch (selectedType) {
                    case "cardio":
                        perfectSport = "Running";
                        break;
                    case "strength":
                        perfectSport = "Climbing";
                        break;
                    case "flexibility":
                        perfectSport = "Yoga";
                        break;
                    default:
                        perfectSport = "Yoga";

                }
            }
        }

        TextView sportSelection = findViewById(R.id.sportTextView);
        if (perfectSport.length() > 1) {
            sportSelection.setText(perfectSport + " is the right sport for you.");
        }
    }
}
