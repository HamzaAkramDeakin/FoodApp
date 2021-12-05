package com.example.hamza.orderfood;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hamza on 23-Mar-17.
 */

public class Customize extends ArrayAdapter<String> {

    private String[] web;

    private Bitmap[] bitmaps;
    private Activity context;


    public Customize(Activity context, String[] web, Bitmap[] bitmaps) {
        super(context,R.layout.categories,web);
        this.context = context;
        this.web= web;
        this.bitmaps= bitmaps;

    }


    public View getView(int position,View view,ViewGroup parent)
    {


        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.categories, null, true);
            /*
            Getting textviews and image views
             */
        TextView name = (TextView) rowView.findViewById(R.id.name);

        ImageView image = (ImageView) rowView.findViewById(R.id.img);

            /*
            Setting the textviiews and imageviews
             */
        name.setText(web[position]);
        Bitmap smaller = Bitmap.createScaledBitmap(bitmaps[position], 400, 200, false);
        image.setImageBitmap(smaller);

            /*
            Returning the whole row which consists on all the elemets added
             */
        return rowView;
    }

}
