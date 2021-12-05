package com.example.hamza.orderfood;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.id;

public class Verification extends AppCompatActivity {
    EditText tx;
    EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        if (ifExists() == false) {
            ed1 = (EditText) findViewById(R.id.name);

            TelephonyManager tMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String mPhoneNumber = tMgr.getLine1Number();
            tx = (EditText) findViewById(R.id.phone);
            tx.setText(mPhoneNumber);
        }
        else{
            Intent i = new Intent(this,getRestaurants.class);



            SQLiteDatabase myDB = null;



            myDB = this.openOrCreateDatabase("UserInfo", MODE_PRIVATE, null);

            Cursor res = myDB.rawQuery( "select * from User", null );
            res.moveToFirst();


            String Name =  res.getString(res.getColumnIndex("name"));
            int Phone =  res.getInt(res.getColumnIndex("phone"));
            i.putExtra("User",Name);
            i.putExtra("Phone",Phone);
            startActivity(i);
        }


    }

    public void VerifyPhone(String Phone) {
        SmsManager sm = SmsManager.getDefault();
        String msg = "Hahahaha";
        sm.sendTextMessage(Phone, null, msg, null, null);
    }

    public void Verify(View view) {
        String User;
        SQLiteDatabase myDB = null;
        String TableName = "User";


        String name = ed1.getText().toString();
        int phone = 123312; //Integer.valueOf(tx.getText().toString());
        String Address = "Hahahaha";


        myDB = this.openOrCreateDatabase("UserInfo", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + TableName
                + " (name VARCHAR(200),phone int(4),address VARCHAR(200) );");
        if (ifExists() == false) {
          /* Create a Table in the Database. */

         /* Insert data to a Table*/
            myDB.execSQL("insert into User (name,phone,address)" + "values("+"\"" + name + "\""+ "," + phone + ","
                    + "\"" + Address + "\"" + ") ;");
           Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            // line 10  + " VALUES ("+ a + ","+ b + ","+ docnam +");");


        }
        else
        {
            Intent i = new Intent(this,getRestaurants.class);




            myDB = this.openOrCreateDatabase("UserInfo", MODE_PRIVATE, null);

            Cursor res = myDB.rawQuery( "select * from User", null );
            res.moveToFirst();


                String Name =  res.getString(res.getColumnIndex("name"));
                int Phone =  res.getInt(res.getColumnIndex("phone"));
               i.putExtra("User",Name);
               i.putExtra("Phone",Phone);
               startActivity(i);


        }
    }
    public boolean ifExists()
    {
        SQLiteDatabase db= null;
        db = this.openOrCreateDatabase("UserInfo", MODE_PRIVATE, null);
        Cursor cursor = null;
        String checkQuery = "SELECT * FROM User ";
        cursor= db.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
