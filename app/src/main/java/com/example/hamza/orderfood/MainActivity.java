package com.example.hamza.orderfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.


    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
    String name,username,userpass;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
ctx = this;
       /* ET_NAME = (EditText)findViewById(R.id.editText4);
        ET_USER_NAME =(EditText)findViewById(R.id.editText5);
        ET_USER_PASS = (EditText)findViewById(R.id.editText6);*/

  //   Chalja();
    }

    public void Chalja(){
        String method = "register";
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(method,"hamza","akram","hahaha");
            Toast.makeText(getApplicationContext(),"Haha1",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item:
                proceed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void proceed(){
       Toast.makeText(getApplicationContext(),"Hahahaha",Toast.LENGTH_LONG).show();
    }

    public void UserReg(View view){
        name = ET_NAME.getText().toString();
        username = ET_USER_NAME.getText().toString();
        userpass = ET_USER_PASS.getText().toString();
        String method = "register";
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(method,name,username,userpass);

    }



public void Check(){

}
    public void open(View view) {
      Intent i = new Intent(this,MapsActivity.class);
        startActivity(i);


      /*  NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_ic_googleplayservices)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

                            builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));*/

    }

    public void ImageShow(View view) {
        Intent i  = new Intent(this,LoadImages.class);
        startActivity(i);
    }
}
