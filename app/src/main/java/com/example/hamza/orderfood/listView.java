package com.example.hamza.orderfood;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class listView extends AppCompatActivity {
ListView lv;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
      lv = (ListView)findViewById(R.id.lists);
    }
private  void getData(){
    String login_url = "http://192.168.134.2/getrestaurants.php";
    URL url;
    try {
        url = new URL(login_url);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        InputStream inputStream  = new BufferedInputStream(httpURLConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
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
}

