package com.example.mobilecarworkshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class CustomerLocation extends AppCompatActivity {
    public static final String TAG = "TAG";
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Button confirmBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID ,  mTypeCar,mTypeCompany,mProblem ;

    CharSequence name,phone;
    TextView mFname , mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_location);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        confirmBtn = findViewById(R.id.confirm);
        fAuth        = FirebaseAuth.getInstance();
        fStore       = FirebaseFirestore.getInstance();
        mFname =    findViewById(R.id.fName);
        mPhone =    findViewById(R.id.phone);



        client = LocationServices.getFusedLocationProviderClient(this);
        userID = fAuth.getCurrentUser().getUid();
        final DocumentReference documentReference2 = fStore.collection("users").document(userID);
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                mPhone.setText(documentSnapshot.getString("phone"));

                mFname.setText(documentSnapshot.getString("fName"));
                name = mFname.getText();
                phone = mPhone.getText();

            }
        });



        if (ActivityCompat.checkSelfPermission(CustomerLocation.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();



        }
        else{

            ActivityCompat.requestPermissions(CustomerLocation.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }

    }

    private void getCurrentLocation() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {

                if(location != null){

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {


                            LatLng latLng = new LatLng(location.getLatitude(),
                                    location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("My current position ");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                            googleMap.addMarker(options);
                        }
                    });
                    final String lat = String.valueOf(location.getLatitude());
                    final String lng = String.valueOf(location.getLongitude());

                    confirmBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("CustomerRequest").document(userID);
                            mTypeCompany = getIntent().getStringExtra("CompanyType");
                            mTypeCar     = getIntent().getStringExtra("carType");
                            mProblem     = getIntent().getStringExtra("problem");







                            Map<String,Object> user = new HashMap<>();
                            user.put("CompanyType",mTypeCompany);
                            user.put("carType",mTypeCar);
                            user.put("problem",mProblem);
                            user.put("lat",lat);
                            user.put("lng",lng);
                            user.put("fName",name);
                            user.put("phone",phone);


                            startActivity(new Intent(getApplicationContext(),ConfirmRequest.class));

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: Request created successfully! "+userID);
                                }
                            });

                        }
                    });
                }

            }
        });



    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){


                getCurrentLocation();
            }
        }
    }



}