package com.lucasdachman.lucas_dachman_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText pizzaNameView;
    ToggleButton sauceToggle;
    Spinner sizeSpinner;
    RadioGroup crustRadioGroup;
    CheckBox pepCheck, sausageCheck, mushCheck, onionCheck;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaNameView = findViewById(R.id.pizza_name);
        sauceToggle = findViewById(R.id.sauce_toggle);
        sizeSpinner = findViewById(R.id.size_spinner);
        crustRadioGroup = findViewById(R.id.crust_radio_group);
        pepCheck = findViewById(R.id.pep_check);
        sausageCheck = findViewById(R.id.sausage_check);
        onionCheck = findViewById(R.id.onions_check);
        mushCheck = findViewById(R.id.mushroom_check);
        resultTextView = findViewById(R.id.result_text);
    }

    public void generate(View view) {
        String name, sauce, size, crust;
        ArrayList<String> toppingsList = new ArrayList<>();

        name = pizzaNameView.getText().toString();
        if (name == null || name.length() == 0) {
            makeToast();
            return;
        }
        sauce = sauceToggle.getText().toString().toLowerCase();
        int sizeIndex = sizeSpinner.getSelectedItemPosition();
        size = getResources().getStringArray(R.array.sizes)[sizeIndex].toLowerCase();
        RadioButton rb = findViewById(crustRadioGroup.getCheckedRadioButtonId());
        if (rb == null) {
            makeToast();
            return;
        }
        crust = rb.getText().toString().toLowerCase();
        CheckBox[] boxes = {pepCheck, sausageCheck, mushCheck, onionCheck};
        for (CheckBox box : boxes) {
            if (box.isChecked()) {
                toppingsList.add(box.getText().toString().toLowerCase());
            }
        }
        String toppings = "";
        for (int i = 0; i < toppingsList.size() - 1; i++) {
           toppings += toppingsList.get(i) + ", ";
        }
        if (toppingsList.size() > 0)
            toppings += "and " + toppingsList.get(toppingsList.size() - 1);
        else
            toppings = "and no toppings";

        String result = String.format("The %s pizza is a %s %s crust pizza with %s sauce, cheese, %s.", name, size, crust, sauce, toppings);
        resultTextView.setText(result);
    }

    public void makeToast() {
       Toast.makeText(getApplicationContext(),
               "Please fill out all options", Toast.LENGTH_LONG)
               .show();
    }
}
