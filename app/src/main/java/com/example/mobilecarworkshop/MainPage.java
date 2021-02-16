package com.example.mobilecarworkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button loginBTN = (Button)findViewById(R.id.LoginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(startIntent);
            }
        });

        Button signUpBTN = (Button)findViewById(R.id.Sign_upBTN);
        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent2 = new Intent(getApplicationContext(),Regeister.class);
                startActivity(startIntent2);
            }
        });

        Button supportBTN = (Button)findViewById(R.id.supportBTN);
        supportBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent3 = new Intent(getApplicationContext(),support.class);
                startActivity(startIntent3);
            }
        });


    }
}