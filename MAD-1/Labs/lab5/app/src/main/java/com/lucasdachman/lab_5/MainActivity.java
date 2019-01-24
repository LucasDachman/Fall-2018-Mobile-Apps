package com.lucasdachman.lab_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView.setImageAlpha(0);
    }

    public void go(View view) {

        TextView textView = findViewById(R.id.textView);
        EditText editText = findViewById(R.id.editText);
        String name = editText.getText().toString();
        if (name.compareTo("") == 0) {
            textView.setText("Welcome!");
            imageView.setImageAlpha(0);
        } else {
            textView.setText("Welcome, " + name + "!");
            //imageView.setImageResource(R.drawable.hello_android);
            imageView.setImageAlpha(255);
        }

    }
}
