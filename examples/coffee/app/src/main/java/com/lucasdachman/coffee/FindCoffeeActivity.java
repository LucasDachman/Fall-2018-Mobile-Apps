package com.lucasdachman.coffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class FindCoffeeActivity extends AppCompatActivity {

    private CoffeeShop coffeeShop = new CoffeeShop();
    private String TAG = "FindCoffeeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_coffee);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCoffee();
                Intent intent = new Intent(getApplicationContext(), ReceiveCoffeeActivity.class);
                intent.putExtra("suggestion", coffeeShop.getCoffeeShop());
                intent.putExtra("URL", coffeeShop.getCoffeeShopURL());
                startActivity(intent);
            }
        });
    }

    private void findCoffee() {
        Spinner spinner = findViewById(R.id.spinner);
        coffeeShop.setCoffeeShop(spinner.getSelectedItemPosition());
        String suggestion = coffeeShop.getCoffeeShop();
        String suggestionURL = coffeeShop.getCoffeeShopURL();
        Log.i(TAG, "suggestion: " + suggestion);
        Log.i(TAG, "url: " + suggestionURL);
    }
}
