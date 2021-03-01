package com.example.mobilecarworkshop;

import android.content.Intent;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Workshop_login extends AppCompatActivity {


    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_login);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mCreateBtn = findViewById(R.id.CreateText);
        progressBar = findViewById(R.id.progressBar);
        mLoginBtn = findViewById(R.id.LoginBtn);
        fAuth = FirebaseAuth.getInstance();


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    mEmail.setError("Email is Required");

                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {

                    mPassword.setError("Password must be more than 6 characters");

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authnication the user account
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Workshop_login.this, "Logged in Successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Workshop_main.class));
                        } else {

                            Toast.makeText(Workshop_login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Workshop_regeister.class));
            }
        });

        Button supportBTNlog = (Button)findViewById(R.id.supportBTNlog);
        supportBTNlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent3 = new Intent(getApplicationContext(),support.class);
                startActivity(startIntent3);
            }
        });
    }
}