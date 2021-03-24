package com.example.mobilecarworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;



public class ProfileW extends AppCompatActivity {


    TextView fullNamek, emailk,phonek,test1,test2;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userIDk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_w);

        fullNamek = findViewById(R.id.nameProfileW);
        emailk    = findViewById(R.id.emailProfileW);
        phonek    = findViewById(R.id.phoneProfileW);

        fAuth    = FirebaseAuth.getInstance();
        fStore   = FirebaseFirestore.getInstance();

        userIDk   = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("workshop").document(userIDk);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                phonek.setText(documentSnapshot.getString("phone"));
                emailk.setText(documentSnapshot.getString("email"));
                fullNamek.setText(documentSnapshot.getString("fName"));
            }
        });


    }
}