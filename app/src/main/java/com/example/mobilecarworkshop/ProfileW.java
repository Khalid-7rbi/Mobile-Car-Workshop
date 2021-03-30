package com.example.mobilecarworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;



public class ProfileW extends AppCompatActivity {


    TextView fullNamek, emailk,phonek,cName,cCar,cCompany,cProblem,cPhone;

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


        cName     = findViewById(R.id.textView18);
        cCompany  = findViewById(R.id.textView20);
        cCar      = findViewById(R.id.textView22);
        cProblem  = findViewById(R.id.textView24);
        cPhone    = findViewById(R.id.textView26);

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

        final DocumentReference documentReference1 = fStore.collection("RequestClosed").document(userIDk);
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                cName.setText(documentSnapshot.getString("CName"));
                cCompany.setText(documentSnapshot.getString("CompanyType"));
                cCar.setText(documentSnapshot.getString("carType"));
                cPhone.setText(documentSnapshot.getString("Cphone"));
                cProblem.setText(documentSnapshot.getString("problem"));
            }
        });



        ImageButton arr = (ImageButton) findViewById (R.id.Arrow);
        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(),Workshop_main.class);
                startActivity(startIntent);

            }
        });


    }
}