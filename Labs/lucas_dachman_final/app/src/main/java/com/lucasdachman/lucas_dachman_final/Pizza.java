package com.lucasdachman.lucas_dachman_final;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pizza {
    private String name, sauce, size, crust, suggestion;
    private ArrayList<String> toppingsList;

    public Pizza() {

    }

    public Pizza(String name, String sauce, String size, String crust, ArrayList<String> toppingsList) {
        this.name = name;
        this.sauce = sauce;
        this.size = size;
        this.crust = crust;
        this.toppingsList = toppingsList;
        setSuggestion();
    }

    private void setSuggestion() {
        if (crust.toLowerCase().equals("thick")) {
            suggestion = "Backcountry Pizza";
        } else {
            suggestion = "Pizzeria Locale";
        }    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getSuggestionURL() {
        if (suggestion.equals("Backcountry Pizza")) {
            return "https://backcountrypizzaandtaphouse.info/";
        } else {
            return "https://localeboulder.com/";
        }
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
        setSuggestion();
    }

    public ArrayList<String> getToppingsList() {
        return toppingsList;
    }

    public void setToppingsList(ArrayList<String> toppingsList) {
        this.toppingsList = toppingsList;
    }
}
