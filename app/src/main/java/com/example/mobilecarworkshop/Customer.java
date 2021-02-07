package com.example.mobilecarworkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Customer extends AppCompatActivity {


    TextView fullName,email,phone;

    Button profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


    }

    public void profile(View view){

        startActivity(new Intent(getApplicationContext(),Profile.class));

    }

    public void logout(View view){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();

    }
}