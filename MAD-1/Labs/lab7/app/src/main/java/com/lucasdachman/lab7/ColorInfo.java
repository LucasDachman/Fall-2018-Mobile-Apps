package com.lucasdachman.lab7;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

public class ColorInfo {

    private int color;
    private String name;
    private String url;

    public ColorInfo(int color, String name, String url) {
        this.color = color;
        this.name = name;
        this.url = url;
    }

    public ColorDrawable getColorDrawable() {
        return new ColorDrawable(this.color);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    static ArrayList<ColorInfo> generateColors() {
        return new ArrayList<ColorInfo>() {
            {
                add(new ColorInfo(Color.RED, "Red", "https://en.wikipedia.org/wiki/Red"));
                add(new ColorInfo(Color.BLUE, "Blue", "https://en.wikipedia.org/wiki/Blue"));
                add(new ColorInfo(Color.GREEN, "Green", "https://en.wikipedia.org/wiki/Green"));
                add(new ColorInfo(Color.YELLOW, "Yellow", "https://en.wikipedia.org/wiki/Yellow"));
            }
        };
    }
}
