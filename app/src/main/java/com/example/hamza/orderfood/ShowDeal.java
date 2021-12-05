package com.example.hamza.orderfood;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ShowDeal extends AppCompatActivity {
int DealCount;
    String value;
    Bitmap imageId;
    String name;
    int Price;
    int id;
    String Quantity;
    String restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deal);
        DealCount = 1;
        TextView tx = (TextView)findViewById(R.id.textView1);
        tx.setText("Items : "+DealCount);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        restaurant = extras.getString("rest");
        Price = Integer.valueOf(extras.getString("price"));
        String value = extras.getString("p_id");
        id = Integer.valueOf(value);

       // getAllCotacts();
       // Cart(1,"Hello",1,1);
       getFoodItem(value);


        TextView txt = (TextView)findViewById(R.id.textview);
        ImageView imageView = (ImageView)findViewById(R.id.img);


        imageView.setImageBitmap(imageId);
        txt.setText(name);




    }

    public void inc(View view){

        DealCount++;
        TextView txt = (TextView)findViewById(R.id.counter);
        txt.setText("Items : "+DealCount);
    }
    public void dec(View view){
        if(DealCount>1) {
            DealCount--;
            TextView txt = (TextView) findViewById(R.id.counter);
            txt.setText("Items : "+DealCount);
        }
    }
    String login_url = "http://192.168.182.1/getDeal.php";


/*
The hello function is used to get the data from url and showing in xaml format by using json parsing .
 */
    public void getFoodItem(String Id){

        URL url;
        try {
            url = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String response = "";
            String line = "";
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine())!=null){
                sb.append(line+"\n");
            }

            bufferedReader.close();
            inputStream.close();
            response = sb.toString();
            JSONArray jsonArray= new JSONArray(response);
            JSONObject jsonObject = null;


            for(int i = 0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                name = jsonObject.getString("name");
                String imageURL = jsonObject.getString("image");
                imageId = getURLImage("http://192.168.182.1/foods/"+imageURL);
                //  Toast.makeText(getApplicationContext(),web[i].toString(), Toast.LENGTH_LONG).show();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<String> getAllData() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase myDB= null;
        String TableName = "Cart";


        myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);

        Cursor res = myDB.rawQuery( "select * from Cart", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
           String name =  res.getString(res.getColumnIndex("name"));
           String id =  res.getString(res.getColumnIndex("id"));
           String price =  res.getString(res.getColumnIndex("price"));
           String Qty =  res.getString(res.getColumnIndex("Qty"));
            String result = name+"    "+price+"    "+Qty+"";
            array_list.add(result);
            res.moveToNext();
        }

        return array_list;
    }
    /*
      This cart function is for entering data into cart and checking that is already in cart then it only updates the quantity of the product
     */
    public void Cart(View view){
        SQLiteDatabase myDB= null;
        String TableName = "Cart";


        try{

            myDB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TableName
                    + " (id INT(4), Qty INT(4),price int(4),name VARCHAR(200) );");
            if(ifExists(name)==false) {
          /* Create a Table in the Database. */

         /* Insert data to a Table*/
                myDB.execSQL("insert into Cart (id,Qty,price,name)" + "values(" + id + "," + DealCount + "," + Price + ","
                        + "\"" + name + "\"" + ") ;");
                Toast.makeText(ShowDeal.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,Cart.class);
                i.putExtra("rest",restaurant);
                startActivity(i);
                finish();
                // line 10  + " VALUES ("+ a + ","+ b + ","+ docnam +");");
                //  line 11  +"VALUES (5011,4011,'gupta');");
            }
            else{
                SQLiteDatabase DB= null;
                DB = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);
                ContentValues cv = new ContentValues();
                cv.put("Qty",DealCount); //These Fields should be your String values of actual column names

                DB.update("Cart", cv, "id="+id, null);
                Toast.makeText(this, "Already Exist", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,Cart.class);
                i.putExtra("rest",restaurant);
                startActivity(i);
                finish();
            }


        }
        catch(Exception e) {
            Log.e("Error", "Error", e);
        }
        finally {
            if (myDB != null)
                myDB.close();
        }
    }
    /*
    IfExists is the function which is used to check the record already exists in the cart or not !
     */
    public boolean ifExists(String name)
    {
        SQLiteDatabase db= null;
        db = this.openOrCreateDatabase("foodordering", MODE_PRIVATE, null);
        Cursor cursor = null;
        String checkQuery = "SELECT * FROM Cart WHERE name = '"+ name + "'";
        cursor= db.rawQuery(checkQuery,null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    /*
    the getURLImage function is used to get the url image into bitmap form and showing the the listview
     */
    public static Bitmap getURLImage(String path)
    {

        try
        {
            URL aURL = new URL(path);
            final URLConnection conn = aURL.openConnection();
            conn.connect();
            final BufferedInputStream bis = new BufferedInputStream(
                    conn.getInputStream());
            final Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            return bm;
        }
        catch (Throwable e)
        {
            Log.w("ERROR", "Error " + e.getMessage());
            return null;
        }
    }

}
