package com.lucasdachman.lab6;

import android.widget.ImageView;

public class Page {
    public String imageName;
    public String title;
    public String description;

    Page(String title, String description, String imageName) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
    }

}
