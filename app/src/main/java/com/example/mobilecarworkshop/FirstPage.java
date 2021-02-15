package com.example.mobilecarworkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Button customerF = (Button)findViewById(R.id.CustomerF);
        customerF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(startIntent);
            }
        });


        Button WorkshopF = (Button)findViewById(R.id.WorkshopF);
        WorkshopF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent2 = new Intent(getApplicationContext(),Regeister.class);
                startActivity(startIntent2);
            }
        });




    }

}
