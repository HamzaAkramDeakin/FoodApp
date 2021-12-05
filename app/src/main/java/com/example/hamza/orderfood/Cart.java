package com.example.hamza.orderfood;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Cart extends AppCompatActivity {
    ListView lv;
    int totalAmt = 1;
    String[] Price;
    String[] Qty;
    String[] Name;
    ListView list;
    String rest;
    //  String textViewMain;
    String LatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);








        TextView txt = (TextView)findViewById(R.id.tr);

        txt.setVisibility(txt.INVISIBLE);

      //  Bundle extras = getIntent().getExtras();
       // rest= extras.getString("rest");

        lv = (ListView)findViewById(R.id.list);
        getAllRecords();

        list=(ListView)findViewById(R.id.list);
        CustomCartChange customCartChange= new CustomCartChange(this,Name,Qty,Price);




        list.setAdapter(customCartChange);

        TextView txt1 = (TextView)findViewById(R.id.total);
        txt1.setText(""+totalAmt);
        lv.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowDeal.class);
                intent.putExtra("name","" +Name[+ position]);
                intent.putExtra("rest","" +rest);
                intent.putExtra("price","" +Price[+ position]);
                intent.putExtra("qty","" +Qty[+ position]);

                startActivity(intent);

                finish();
               // Toast.makeText(this,"",Toast.LENGTH_SHORT).show();


            }
        });







    }






    /*
    Delete a Selected Deal from Cart
     */
   /* public void Delete(View view){

        SQLiteDatabase myDB= null;
        String TableName = "Cart";
        //" + "\"" + Name[+ 1]+ "\"" +""
        myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);
        myDB.execSQL("DELETE FROM `cart` WHERE name = "+"Burger Combo"+"");
        Toast.makeText(getApplicationContext(),"Item Deleted",Toast.LENGTH_LONG).show();
        /*lv = (ListView)findViewById(R.id.list);
        getAllRecords();

        list=(ListView)findViewById(R.id.list);

        CartCustomList cartCustomList = new CartCustomList(this,Name,Qty,Price);


        list.setAdapter(cartCustomList);

    }*/

public void Change(View view){
    TextView txt = (TextView)findViewById(R.id.tr);
    txt.setVisibility(txt.INVISIBLE);
    TextView txt2 = (TextView)findViewById(R.id.edit);
    txt2.setVisibility(txt2.VISIBLE);
    txt.setEnabled(true);


    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    getAllRecords();
    list=(ListView)findViewById(R.id.list);

    CartCustomList cartCustomList = new CartCustomList(this,Name,Qty,Price);

    list.setAdapter(cartCustomList);

    TextView txt1 = (TextView)findViewById(R.id.total);
    txt1.setText(""+totalAmt);


}
    public void Delete(View view){

        TextView txt = (TextView)findViewById(R.id.tr);
        txt.setVisibility(txt.VISIBLE);
        TextView txt2 = (TextView)findViewById(R.id.edit);
        txt2.setVisibility(txt2.INVISIBLE);
        txt.setEnabled(true);

        final int position = list.getPositionForView((View) view.getParent());
        SQLiteDatabase myDB= null;
        String TableName = "Cart";
        //" + "\"" + Name[+ 1]+ "\"" +""
        myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);
        myDB.execSQL("DELETE FROM Cart WHERE name =  '"+ Name[+position] + "'");
        myDB.close();
        Toast.makeText(getApplicationContext(),"Item Deleted",Toast.LENGTH_LONG).show();
        list.setAdapter(null);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        getAllRecords();
        list=(ListView)findViewById(R.id.list);

        CartCustomList cartCustomList = new   CartCustomList(this,Name,Qty,Price);

        list.setAdapter(cartCustomList);

        TextView txt1 = (TextView)findViewById(R.id.total);
        txt1.setText(""+totalAmt);
    }

    public void getAllRecords() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase myDB= null;
        String TableName = "Cart";


        myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);

        Cursor res = myDB.rawQuery( "select * from Cart", null );
        res.moveToFirst();
        Name = new String[res.getCount()];
        Price = new String[res.getCount()];
        Qty = new String[res.getCount()];
        totalAmt = res.getCount();
        int i = 0;

        while((res.isAfterLast() == false)&&(i<=res.getCount())){
            Name[i] =  res.getString(res.getColumnIndex("name"));
            int id =  res.getInt(res.getColumnIndex("id"));
            Price[i]=  String.valueOf(Integer.valueOf(res.getString(res.getColumnIndex("Qty")))*res.getInt(res.getColumnIndex("price")));
            totalAmt = totalAmt + Integer.valueOf(res.getString(res.getColumnIndex("Qty")))*res.getInt(res.getColumnIndex("price"))-1;
            Qty[i] =  res.getString(res.getColumnIndex("Qty"));

            i++;




            res.moveToNext();
        }



    }







    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent)
        {
            //do w/e
        }
    }

public void Runs(){
    Date when = new Date(System.currentTimeMillis());

    try{
         // intent to be launched
        Intent someIntent = new Intent(this,MyReceiver.class);
        // note this could be getActivity if you want to launch an activity
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0, // id, optional
                someIntent, // intent to launch
                PendingIntent.FLAG_CANCEL_CURRENT); // PendintIntent flag

        AlarmManager alarms = (AlarmManager) this.getSystemService(
                Context.ALARM_SERVICE);

        alarms.setRepeating(AlarmManager.RTC_WAKEUP,
                when.getTime(),
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                pendingIntent);

    }catch(Exception e){
        e.printStackTrace();
    }
}





public void ApplyChange(View view){
    TextView txt = (TextView)findViewById(R.id.tr);
    txt.setVisibility(txt.INVISIBLE);
    TextView txt2 = (TextView)findViewById(R.id.edit);
    txt2.setVisibility(txt2.VISIBLE);
    txt.setEnabled(true);


    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
    getAllRecords();
    list=(ListView)findViewById(R.id.list);

    CustomCartChange cartCustomList = new CustomCartChange(this,Name,Qty,Price);

    list.setAdapter(cartCustomList);

    TextView txt1 = (TextView)findViewById(R.id.total);
    txt1.setText(""+totalAmt);
}













    public void proceed(View view){
        Calendar c = Calendar.getInstance();
        String seconds = String.valueOf(c.get(Calendar.SECOND));
        String minutes= String.valueOf(c.get(Calendar.MINUTE));
        String hours =String.valueOf( c.get(Calendar.HOUR));


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it



        DatePicker dt = null;
        Background2 background2 = new Background2(this);
       background2.execute(rest,"2","hamza",String.valueOf(LatLng),currentDateTimeString,hours+":"+minutes+":"+seconds);
      ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase myDB= null;
        String TableName = "Cart";
        myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);

        Cursor res = myDB.rawQuery( "select * from Cart", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            String name =  res.getString(res.getColumnIndex("name"));
            int id =  res.getInt(res.getColumnIndex("id"));
            int price =  res.getInt(res.getColumnIndex("price"));
            String Qty =  res.getString(res.getColumnIndex("Qty"));
            String result = name+" "+price+" "+Qty+""+id;
            String method = "register";
            BackgroundTask bg = new BackgroundTask(this);
            bg.execute(method,""+id,String.valueOf(rest),"2",name,""+price,Qty);

          //  Toast.makeText(this,""+result,Toast.LENGTH_SHORT).show();

            res.moveToNext();
        }
Toast.makeText(getApplicationContext(),"Your Order is Submitted",Toast.LENGTH_LONG).show();
        myDB.execSQL("delete from Cart");
       /* public boolean deleteTitle(String name)
        {
            return db.delete(DATABASE_TABLE, KEY_NAME + "=" + name, null) > 0;
        }*/



    }

}
