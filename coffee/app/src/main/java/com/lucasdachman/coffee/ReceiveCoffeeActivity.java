package com.lucasdachman.coffee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReceiveCoffeeActivity extends AppCompatActivity {

    private String coffeeShop;
    private String coffeeShopURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_coffee);

        Intent intent = getIntent();
        coffeeShop = intent.getStringExtra("suggestion");
        coffeeShopURL = intent.getStringExtra("URL");

        TextView suggestionView = findViewById(R.id.suggestedCoffee);
        suggestionView.setText(coffeeShop);

        findViewById(R.id.webButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebsite(v);
            }
        });
    }

    private void loadWebsite(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(coffeeShopURL));
        startActivity(intent);
    }
}
