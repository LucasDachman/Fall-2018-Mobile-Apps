package com.lucasdachman.lucas_dachman_final;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ArrayList<Restaurant> restaurants = RestaurantStore.getInstance().restaurants;
    private RequestQueue requestQueue;
    private Adapter adapter;
    private String url = "https://creative.colorado.edu/~apierce/samples/data/yelp_restaurants.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(restaurants, getSupportFragmentManager());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRestaurantDialogFragment frag = new EditRestaurantDialogFragment();
                frag.setOnDataChangedListener(adapter);
                frag.show(getSupportFragmentManager(), TAG);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestQueue = Volley.newRequestQueue(this);
        requestRestaurants();
    }

    private void requestRestaurants() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "received json");
                        parseResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });

        // Add the request to the RequestQueue.
        Log.i(TAG, "adding request to queue");
        requestQueue.add(jsonRequest);
    }

    private void parseResponse(JSONObject response) {
        try {
            JSONArray list = response.getJSONArray("restaurants");
            ArrayList<Restaurant> fetchedRestaurants = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                JSONObject object = list.getJSONObject(i);
                String name = object.getString("name");
                String url = object.getString("url");
                fetchedRestaurants.add(new Restaurant(name, url));
            }
            Log.i(TAG, "finished parsing json");
            RestaurantStore.getInstance().restaurants.clear();
            RestaurantStore.getInstance().restaurants.addAll(fetchedRestaurants);
            adapter.onDataChanged();
        } catch (JSONException e) {
            System.err.println("Error parsing response");
            System.err.println(e);
        }
    }
}
