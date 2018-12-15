package com.lucasdachman.lucas_dachman_final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    Pizza pizza;

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
        if (name.length() == 0) {
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

        pizza = new Pizza(name, sauce, size, crust, toppingsList);
        String result = pizza.toString();
        resultTextView.setText(result);
    }


    public void makeToast() {
       Toast.makeText(getApplicationContext(),
               "Please fill out all options", Toast.LENGTH_LONG)
               .show();
    }

    public void goToSuggestion(View view) {
        generate(null);
        String suggestion, url;
        try {
           suggestion = pizza.getSuggestion();
           url = pizza.getSuggestionURL();
        } catch (Exception e) {
           makeToast();
           return;
        }
        Intent intent = new Intent(this, SuggestionActivity.class);
        intent.putExtra("SUGGESTION", suggestion);
        intent.putExtra("URL", url);
        startActivity(intent);
    }
}
