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

public class RegLocation extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;

    RegLocation(Context ctx) {
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
        String method = "register";
        String status= params[0];
        String sid = params[1];
        String src = params[2];
        String did = params[3];
        String des = params[4];



        String login_url = "http://faiscouture.com/insertLocations.php";
        if (status == "S") {

            URL url;
            try {
                url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");


                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("sid", "UTF-8") + "=" + URLEncoder.encode(sid, "UTF-8") + "&" +
                        URLEncoder.encode("src", "UTF-8") + "=" + URLEncoder.encode(src, "UTF-8") + "&" +
                        URLEncoder.encode("did", "UTF-8") + "=" + URLEncoder.encode(did, "UTF-8") + "&" +
                        URLEncoder.encode("des", "UTF-8") + "=" + URLEncoder.encode(des, "UTF-8");
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
        else{

            URL url;
            try {
                url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");


                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("", "UTF-8") + "=" + URLEncoder.encode(did, "UTF-8") + "&" +
                        URLEncoder.encode("des", "UTF-8") + "=" + URLEncoder.encode(des, "UTF-8");
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