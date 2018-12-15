package com.lucasdachman.lucas_dachman_final;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SuggestionActivity extends AppCompatActivity {
    private String suggestion, url;
    private TextView suggestionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        Intent intent = getIntent();
        suggestion = intent.getStringExtra("SUGGESTION");
        url = intent.getStringExtra("URL");
        suggestionView = findViewById(R.id.suggestion_text);
        suggestionView.setText("You should try " + suggestion);
    }

    public void goToSite(View view) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

}
