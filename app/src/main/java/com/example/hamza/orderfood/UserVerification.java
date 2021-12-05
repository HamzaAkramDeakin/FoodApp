package com.example.hamza.orderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserVerification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_verification);


    }
    public void GetVerification(View view){
        Intent intent = new Intent(getApplicationContext(),PhoneVerify.class);
        startActivity(intent);
    }

}
