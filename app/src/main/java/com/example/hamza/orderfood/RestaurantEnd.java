package com.example.hamza.orderfood;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
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
import java.util.ArrayList;

public class RestaurantEnd extends Activity {
    String login_url = "http://192.168.182.1/getOrders.php";
    ListView list;
    String[] details ;
    String[] web;
    String[] ids;
    Bitmap[] imageId;
    ArrayList<Bitmap> items = new ArrayList<>();
    String cat;
    private void hello(){

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
            details = new String[jsonArray.length()];
            ids = new String[jsonArray.length()];
            imageId = new Bitmap[jsonArray.length()];
            for(int i = 0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                //ids[i] = jsonObject
                web[i] = jsonObject.getString("cus_name").toString();
                ids[i] = jsonObject.getString("cus_id").toString();
                details[i] = "Food is Ordered By "+web[i]+" At ";



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
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Loading Restaurants..!", true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_end);


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        hello();
        list=(ListView)findViewById(R.id.list);

        CustomRestaurant adapter = new CustomRestaurant(this, web,details);
        list.setAdapter(adapter);
        progressDialog.dismiss();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Restaurant_Orders.class);


                intent.putExtra("Value", ids[+ position]);



                startActivity(intent);
                Toast.makeText(RestaurantEnd.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}