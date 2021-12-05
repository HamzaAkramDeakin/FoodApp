package com.example.hamza.orderfood;

import android.graphics.Bitmap;

/**
 * Created by Hamza on 30-Apr-17.
 */

public class Item {

    private String name;
    private String description;
    private Bitmap image;


    public Item(String name, String description, Bitmap image) {
        this.name= name;
        this.description = description;
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


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    // getters and setters...
}