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

public class CustomCartChange extends ArrayAdapter<String> {

    private String[] Name;
    private String[] Qty;
    private String[] Price;
    private Activity context;


    public CustomCartChange(Activity context, String[] Name,String[] Qty,String[] Price) {
        super(context,R.layout.dealswithout,Name);
        this.context = context;
        this.Name= Name;
        this.Qty= Qty;
        this.Price =Price;
    }


    public View getView(int position,View view,ViewGroup parent)
    {

        LayoutInflater inflater =  context.getLayoutInflater();
        View rowView;
        rowView = inflater.inflate(R.layout.dealswithout, parent, false);

            /*
            Getting text views and image views
             */

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView qty = (TextView) rowView.findViewById(R.id.detail);
        TextView price = (TextView) rowView.findViewById(R.id.Price);

            /*
            Setting the text views and image views
             */
        name.setText(Name[position]);
/*
  Cumshot is nessaccesy
 */
        price.setText("Price : "+Price[position]);
        qty.setText("Quantity : "+Qty[position]);
            /*
            Returning the whole row which consists on all the elements added
             */
        return rowView;
    }

}
