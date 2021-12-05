package com.example.hamza.orderfood;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hamza on 23-Mar-17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;

    BackgroundTask(Context ctx) {
        this.ctx = ctx;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();

        alertDialog.setTitle("login information");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

/*
This doInBackground function is used to insert data in the mysql database by URL encoding techniques
 */

    protected String doInBackground(String... params) {
        String method = params[0];
        String Id = params[1];
        String rest_Id = params[2];
        String cus_Id = params[3];
        String Name = params[4];
        String Price = params[5];
        String Qty = params[6];

        String login_url = "http://192.168.182.1/adCarts.php";
        if (method.equals("register")) {

            URL url;
            try {
                url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");


                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("res_id", "UTF-8") + "=" + URLEncoder.encode(rest_Id, "UTF-8") + "&" +
                        URLEncoder.encode("cus_id", "UTF-8") + "=" + URLEncoder.encode(cus_Id, "UTF-8") + "&" +
                        URLEncoder.encode("p_id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&" +
                        URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(Price, "UTF-8") + "&" +
                        URLEncoder.encode("qty", "UTF-8") + "=" + URLEncoder.encode(Qty, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();

                outputStream.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "Success !";
    }

    @Override
    protected void onPostExecute(String s) {

      //      Toast.makeText(ctx,s, Toast.LENGTH_LONG).show();


    }
}