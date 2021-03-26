package com.example.mobilecarworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Workshop_main extends AppCompatActivity {

    Button profileW;

    Button wLogout;
    Button checkRequests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_main);



    wLogout = findViewById(R.id.logout);
        checkRequests = findViewById(R.id.checkRequests);
        checkRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),recycleView.class);
                startActivity(startIntent);
            }
        });
    wLogout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),FirstPage.class));
            finish();
        }
    });


    }


    public void profileW(View view){

        startActivity(new Intent(getApplicationContext(),ProfileW.class));

    }
}