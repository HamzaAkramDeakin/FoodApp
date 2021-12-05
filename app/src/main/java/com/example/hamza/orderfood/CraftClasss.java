package com.example.hamza.orderfood;

/**
 * Created by Hamza on 26-Mar-17.
 */

public class CraftClasss {


    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    String imgURL;

}
