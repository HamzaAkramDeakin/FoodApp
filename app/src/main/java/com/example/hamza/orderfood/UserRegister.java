package com.example.hamza.orderfood;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class UserRegister extends Activity {
EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
String name,username,userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
     /*   ET_NAME = (EditText)findViewById(R.id.editText4);
        ET_USER_NAME =(EditText)findViewById(R.id.editText5);
        ET_USER_PASS = (EditText)findViewById(R.id.editText6);*/
        String method = "register";
        BackgroundTask bg = new BackgroundTask(this);
        bg.execute(method,"hamza","akram","minhas");

    }
public void UserReg(View view){
    name = ET_NAME.getText().toString();
    username = ET_USER_NAME.getText().toString();
    userpass = ET_USER_PASS.getText().toString();
    String method = "register";
    BackgroundTask bg = new BackgroundTask(this);
    bg.doInBackground(method,name,username,userpass);

}
}
