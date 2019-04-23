package com.lucasdachman.lab_8;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "ITEMS";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load data
        if(data.isEmpty()) {
            loadData(this);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        data = new ArrayList<>(Arrays.asList("Item1", "Item2", "Item3"));

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        data.add(editText.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void storeData(Context context) {
        //get access to a shared preferences file
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        //create an editor to write to the shared preferences file
        SharedPreferences.Editor editor = sharedPrefs.edit();
        //add size to the editor
        editor.putInt("size", data.size());
        //add items to the editor
        for (int i = 0; i < data.size(); i++) {
            editor.putString("item" + i, data.get(i));
        }
        editor.apply();
    }

    public void loadData(Context context) {
        //get access to a shared preferences file
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        //create an editor to read from the shared preferences file
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int size = sharedPrefs.getInt("size", 0);
        if(size>0) {
            for (int i = 0; i < size; i++) {
                data.add(sharedPrefs.getString("item" + i, ""));
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //save data
        storeData(this);
    }
}
