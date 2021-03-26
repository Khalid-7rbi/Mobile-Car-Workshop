package com.example.mobilecarworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class deleteRequest extends AppCompatActivity {

    Button DeleteBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_request);
        DeleteBtn        = findViewById(R.id.deleteBTN);
        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();

       DeleteBtn.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               userID = fAuth.getCurrentUser().getUid();
               DocumentReference documentReference = fStore.collection("CustomerRequest").document(userID);
               fStore.collection("CustomerRequest").document(userID).delete();
               startActivity(new Intent(getApplicationContext(),Customer.class));
           }
       });

        Button cancel = (Button)findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(),Customer.class);
                startActivity(startIntent);

            }
        });

    }
}