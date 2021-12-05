package com.example.hamza.orderfood;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoadImages extends AppCompatActivity {
private ImageView imageView;
private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);
        imageView = (ImageView)findViewById(R.id.imageView1);
        bitmap = getBitmapFromURL("http://192.168.134.2/uploadimages/uploads/one.png");
        imageView.setImageBitmap(bitmap);
    }
public Bitmap getBitmapFromURL(String path) {
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
