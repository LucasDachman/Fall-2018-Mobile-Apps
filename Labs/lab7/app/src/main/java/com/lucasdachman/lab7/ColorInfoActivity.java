package com.lucasdachman.lab7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ColorInfoActivity extends AppCompatActivity {
    String selectedColor;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_info);

        Intent intent = getIntent();
        selectedColor = intent.getStringExtra("name");
        url = intent.getStringExtra("url");

        TextView textView = findViewById(R.id.youPickedText);
        textView.setText(getResources().getString(R.string.youPickedText) + selectedColor);

        Button moreButton = findViewById(R.id.find_out_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
