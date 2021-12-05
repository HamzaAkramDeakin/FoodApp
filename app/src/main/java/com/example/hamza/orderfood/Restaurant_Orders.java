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

public class Restaurant_Orders extends Activity {
    String login_url = "http://192.168.182.1/getCartData.php";
    String NameOfUser;
    String totalAmt;
    TextView UserName;
    TextView TotalAmmount;
    ListView list;
    String[] Price;
    String[] Qty;
    String[] Name;
    private void hello(String value){

        URL url;
        try {
            url = new URL(login_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");


            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
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
            int totalAmt = 1;
            bufferedReader.close();
            inputStream.close();
            response = sb.toString();
            JSONArray jsonArray=
                    new JSONArray(response);
            JSONObject jsonObject = null;
            Price = new String[jsonArray.length()];
            Name = new String[jsonArray.length()];
            Qty = new String[jsonArray.length()];

            for(int i = 0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);

           //    UserName.setText(jsonObject.getString("name").toString());

                Price[i]=jsonObject.getString("price");
                Name[i]=jsonObject.getString("name");
                Qty[i]=jsonObject.getString("qty");
                totalAmt = totalAmt+ Integer.valueOf(Price[i])*Integer.valueOf(Qty[i]);

                //  Toast.makeText(getApplicationContext(),web[i].toString(), Toast.LENGTH_LONG).show();
            }
            TotalAmmount.setText(String.valueOf(totalAmt-1));


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
        setContentView(R.layout.activity_restaurant__orders);
        UserName = (TextView)findViewById(R.id.nameofUser);
       TotalAmmount = (TextView)findViewById(R.id.totalAmt);
        Bundle extras = getIntent().getExtras();

        final String value = extras.getString("Value");

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        hello(value);
        list=(ListView)findViewById(R.id.list);

        CartCustomList cartCustomList = new CartCustomList(this,Name,Qty,Price);
        list.setAdapter(cartCustomList);
        progressDialog.dismiss();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowFoodItems.class);


                intent.putExtra("key","" +Name[+ position]);



                startActivity(intent);
                Toast.makeText(Restaurant_Orders.this, "You Clicked at " +Name[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }

}