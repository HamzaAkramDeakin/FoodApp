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

public class getRestaurants extends Activity {
    String login_url = "http://192.168.182.1/getrestaurants.php";
    ListView list;
    String[] details ;
    String[] web;
    Bitmap[] imageId;
    String[] r_ids;
    ArrayList<Bitmap> items = new ArrayList<>();
String cat;
    private void hello(String value){

            URL url;
        try {
            url = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");


            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("cat", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
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
            JSONArray jsonArray=
                    new JSONArray(response);
            JSONObject jsonObject = null;
           web = new String[jsonArray.length()];
            details = new String[jsonArray.length()];
            imageId = new Bitmap[jsonArray.length()];
            r_ids = new String[jsonArray.length()];
            for(int i = 0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

                web[i] = jsonObject.getString("name").toString();
                details[i] = jsonObject.getString("description").toString();
                r_ids[i] = jsonObject.getString("r_id").toString();
                String imageURL = jsonObject.getString("image");
                imageId[i] = getURLImage("http://192.168.182.1/uploads/"+imageURL);



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
        setContentView(R.layout.activity_get_restaurants);
        Bundle extras = getIntent().getExtras();

          final String value = extras.getString("key");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        hello(value);
        list=(ListView)findViewById(R.id.list);

        CustomList adapter = new CustomList(getRestaurants.this, web, imageId,details);
        list.setAdapter(adapter);
        progressDialog.dismiss();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowFoodItems.class);


                intent.putExtra("R_id","" +r_ids[+ position]);
                intent.putExtra("name","" +web[+ position]);



                startActivity(intent);
                Toast.makeText(getRestaurants.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}