package com.example.hamza.orderfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Orders extends AppCompatActivity {

    String login_url = "http://192.168.182.1/getOrders.php";
    ListView list;
    String[] web1 ;
    String[] web;
    Bitmap[] imageId;


    public void Hello(){

        URL url;
        try {
            url = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setDoOutput(true);
            //  httpURLConnection.setDoInput(true);
            InputStream inputStream  = new BufferedInputStream(httpURLConnection.getInputStream());
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
            JSONArray jsonArray=
                    new JSONArray(response);
            JSONObject jsonObject = null;
            web = new String[jsonArray.length()];
            imageId = new Bitmap[jsonArray.length()];
            for(int i = 0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                web[i] = jsonObject.getString("text").toString();
                String imageURL = jsonObject.getString("image");

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


       // StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Hello();
        list=(ListView)findViewById(R.id.list);

//        CustomList adapter = new
  //              CustomList(Orders.this, web, imageId,web1);




        //list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowDeal.class);


                intent.putExtra("key","" +web[+ position]);



                startActivity(intent);
                Toast.makeText(Orders.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}