package com.example.mobilecarworkshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RequestPage extends AppCompatActivity {


    public static final String TAG = "TAG";
    EditText mTypeCar,mTypeCompany,mProblem;
    Button mNextBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);


        mTypeCar = findViewById(R.id.typeCar);
        mTypeCompany = findViewById(R.id.typeCompany);
        mProblem     = findViewById(R.id.problem);
        mNextBtn        = findViewById(R.id.next);


        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String company = mTypeCompany.getText().toString().trim();
                String car = mTypeCar.getText().toString().trim();
                String problem = mProblem.getText().toString().trim();

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("CustomerRequest").document(userID);

                Map<String,Object> user = new HashMap<>();
                user.put("CompanyType",company);
                user.put("carType",car);
                user.put("problem",problem);


                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Request created successfully! "+userID);
                    }
                });


                startActivity(new Intent(getApplicationContext(), Customer.class));
            }
        });


    }
}