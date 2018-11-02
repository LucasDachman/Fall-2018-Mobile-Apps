package com.lucasdachman.halloween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayBoo(View view) {
        EditText editText = findViewById(R.id.editText);
        String name = editText.getText().toString();
        TextView booText = findViewById(R.id.message);
        booText.setText("Happy Halloween " + name + "!");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ghost);
    }
}
