package com.example.hamza.orderfood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hamza on 23-Mar-17.
 */

public class CustomRestaurant extends ArrayAdapter<String> {

    private String[] web;
    private String[] text;
    private Activity context;


    public CustomRestaurant(Activity context, String[] web, String[] text) {
        super(context,R.layout.orderslayout,web);
        this.context = context;
        this.web= web;
        this.text = text;

    }


    public View getView(int position,View view,ViewGroup parent)
    {


        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.orderslayout, null, true);
            /*
            Getting textviews and image views
             */
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView detail = (TextView) rowView.findViewById(R.id.detail);



            /*
            Setting the textviiews and imageviews
             */
        name.setText(web[position]);
        detail.setText(text[position]);


            /*
            Returning the whole row which consists on all the elemets added
             */
        return rowView;
    }

}
