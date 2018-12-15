package com.lucasdachman.lucas_dachman_final;

import java.util.ArrayList;

public class Pizza {
    private String name, sauce, size, crust;
    private ArrayList<String> toppingsList;

    public Pizza() {

    }

    public Pizza(String name, String sauce, String size, String crust, ArrayList<String> toppingsList) {
        this.name = name;
        this.sauce = sauce;
        this.size = size;
        this.crust = crust;
        this.toppingsList = toppingsList;
    }

    @Override
    public String toString() {
        String toppings = "";
        for (int i = 0; i < toppingsList.size() - 1; i++) {
            toppings += toppingsList.get(i) + ", ";
        }
        if (toppingsList.size() > 0)
            toppings += "and " + toppingsList.get(toppingsList.size() - 1);
        else
            toppings = "and no toppings";
        return String.format(
                "The %s pizza is a %s %s crust pizza with %s sauce, cheese, %s.",
                name, size, crust, sauce, toppings);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public ArrayList<String> getToppingsList() {
        return toppingsList;
    }

    public void setToppingsList(ArrayList<String> toppingsList) {
        this.toppingsList = toppingsList;
    }
}
