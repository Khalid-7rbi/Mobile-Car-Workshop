package com.example.mobilecarworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Workshop_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_main);


    }
    public void info(View view){

        startActivity(new Intent(getApplicationContext(),info.class));

    }

    public void Available(View view){

        startActivity(new Intent(getApplicationContext(),Available.class));

    }

    public void logoutW(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Workshop_login.class));
        finish();

    }
}