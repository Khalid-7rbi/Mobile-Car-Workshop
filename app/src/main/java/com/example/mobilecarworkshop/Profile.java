package com.example.mobilecarworkshop;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Profile extends AppCompatActivity {



    TextView fullName, email,phone,company,car,problem;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullName = findViewById(R.id.nameProfile);
        email    = findViewById(R.id.emailProfile);
        phone    = findViewById(R.id.phoneProfile);

        company  = findViewById(R.id.textView10);
        car      = findViewById(R.id.textView11);
        problem  = findViewById(R.id.textView12);

        fAuth    = FirebaseAuth.getInstance();
        fStore   = FirebaseFirestore.getInstance();

        userID   = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                phone.setText(documentSnapshot.getString("phone"));
                email.setText(documentSnapshot.getString("email"));
                fullName.setText(documentSnapshot.getString("fName"));
            }
        });
        final DocumentReference documentReference1 = fStore.collection("CustomerRequest").document(userID);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                company.setText(documentSnapshot.getString("CompanyType"));
                car.setText(documentSnapshot.getString("carType"));
                problem.setText(documentSnapshot.getString("problem"));
            }
        });


        ImageButton arr = (ImageButton) findViewById (R.id.Arrow);
        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(),Customer.class);
                startActivity(startIntent);

            }
        });

    }
}