package com.lucasdachman.lab_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {
    EditText titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleText = findViewById(R.id.new_task_edit_text);
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                addItem(s.toString());
            }
        });
    }

    private void addItem(String text) {
        // TODO: add item to coresponding list
    }

}
