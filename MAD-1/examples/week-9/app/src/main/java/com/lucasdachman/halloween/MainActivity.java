package com.lucasdachman.halloween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText editText;
    ImageView imageView;
    TextView booText;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        booText = findViewById(R.id.message);

        if (savedInstanceState != null) {
            message = savedInstanceState.getString("msg");
            booText.setText(message);
            imageView.setImageResource(savedInstanceState.getInt("imageid"));
        }
    }

    public void sayBoo(View view) {
        editText = findViewById(R.id.editText);
        String name = editText.getText().toString();
        message = "Happy Halloween " + name + "!";
        booText.setText(message);
        imageView.setImageResource(R.drawable.ghost);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("msg", message);
        outState.putInt("imageid", R.drawable.ghost);
    }
}
