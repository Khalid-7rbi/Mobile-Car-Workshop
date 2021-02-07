package com.example.mobilecarworkshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regeister extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegisterdBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeister2);

        mFullName    = findViewById(R.id.FullName);
        mEmail       = findViewById(R.id.Email);
        mPassword    =  findViewById(R.id.Password);
        mPhone       =  findViewById(R.id.Phone);
        mRegisterdBtn = findViewById(R.id.RegsiterBtn);
        mLoginBtn    = findViewById(R.id.CreateText);

        fAuth        = FirebaseAuth.getInstance();
        progressBar  = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){

            startActivity(new Intent(getApplicationContext(), Customer.class));
            finish();
        }

        mRegisterdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    mEmail.setError("Email is Required");

                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6){

                    mPassword.setError("Password must be more than 6 characters");

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // regstierd the user :)
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(Regeister.this, "User Created ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Customer.class));
                        }
                        else{

                            Toast.makeText(Regeister.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}