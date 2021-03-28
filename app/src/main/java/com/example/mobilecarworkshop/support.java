package com.example.mobilecarworkshop;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class support extends AppCompatActivity {
    EditText need,phone;
    public static final String TAG = "TAG";
    private TextView txtView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Button send = (Button)findViewById(R.id.send);
        phone = findViewById(R.id.number);
        need     = findViewById(R.id.need);
        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();


        txtView=(TextView)findViewById(R.id.sent);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issue = need.getText().toString().trim();
                String contact = phone.getText().toString().trim();
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("supportRequest").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("issue",issue);
                user.put("contact",contact);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: message sent! "+userID);
                        Intent startIntent = new Intent(getApplicationContext(),Customer.class);
                        startActivity(startIntent);
                    }
                });

            }
        });
    }
}