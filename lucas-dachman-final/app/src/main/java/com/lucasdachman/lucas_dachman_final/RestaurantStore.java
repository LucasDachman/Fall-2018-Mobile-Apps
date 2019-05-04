package com.lucasdachman.lucas_dachman_final;

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
        restaurants.add(new Restaurant("Avery Brewing", "https://www.google.com"));
        restaurants.add(new Restaurant("The Buff", "https://www.google.com"));
    }

}

