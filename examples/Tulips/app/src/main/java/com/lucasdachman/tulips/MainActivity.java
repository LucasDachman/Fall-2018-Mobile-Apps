package com.lucasdachman.tulips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new BulbAdapter(new BulbAdapter.ListItemClickListener() {
            @Override
            public void onClick(int position) {
                // deal with click
                Intent intent = new Intent(MainActivity.this, BulbActivity.class);
                intent.putExtra("bulb_id",position);
                startActivity(intent);
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
