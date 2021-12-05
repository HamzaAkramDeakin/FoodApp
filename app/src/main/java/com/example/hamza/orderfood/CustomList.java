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

public class CustomList extends ArrayAdapter<String> {

private String[] web;
private String[] details;
private Bitmap[] bitmaps;
private Activity context;


public CustomList(Activity context, String[] web, Bitmap[] bitmaps,String[] details) {
        super(context,R.layout.list_single,web);
        this.context = context;
        this.web= web;
        this.bitmaps= bitmaps;
        this.details = details;
        }


public View getView(int position,View view,ViewGroup parent)
        {


                LayoutInflater inflater = context.getLayoutInflater();
                View rowView = inflater.inflate(R.layout.list_single, null, true);
            /*
            Getting text views and image views
             */
                TextView name = (TextView) rowView.findViewById(R.id.name);
                TextView description = (TextView) rowView.findViewById(R.id.detail);
                ImageView image = (ImageView) rowView.findViewById(R.id.img);
            /*
            Setting the text views and image views
             */
                name.setText(web[position]);
                Bitmap smaller = Bitmap.createScaledBitmap(bitmaps[position], 100, 50, false);
                image.setImageBitmap(smaller);
                description.setText(details[position]);
            /*
            Returning the whole row which consists on all the elements added
             */
                return rowView;
        }

}
