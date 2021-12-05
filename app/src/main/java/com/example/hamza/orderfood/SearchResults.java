package com.example.hamza.orderfood;

import android.graphics.Bitmap;

/**
 * Created by Hamza on 29-Apr-17.
 */

public class SearchResults {
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getImage() {
        return image;
    }

    private String name = "";
    private String description = "";
    private Bitmap image;
}
