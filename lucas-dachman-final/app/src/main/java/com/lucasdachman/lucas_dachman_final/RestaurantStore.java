package com.lucasdachman.lucas_dachman_final;

import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantStore {
    // static members
    private static RestaurantStore instance;

    public static RestaurantStore getInstance() {
        if (instance == null) {
            instance = new RestaurantStore();
        }
        return instance;
    }

    public ArrayList<Restaurant> restaurants;

    public RestaurantStore() {
        restaurants = new ArrayList<>();
    }
}

