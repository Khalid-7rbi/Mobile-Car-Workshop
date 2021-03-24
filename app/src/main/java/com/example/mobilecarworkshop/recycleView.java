package com.example.mobilecarworkshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import static android.graphics.ColorSpace.*;


public class recycleView extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    RecyclerView recyclerView;

    ArrayList<requestList>list;
    private FirestoreRecyclerAdapter adapter;
    TextView cmp1,phn1,nme1,typ1,prb1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        fAuth    = FirebaseAuth.getInstance();
        fStore   = FirebaseFirestore.getInstance();

        mFirestoreList= findViewById(R.id.myRecycler);



        Query query = fStore.collection("CustomerRequest");

        FirestoreRecyclerOptions<requestList> options= new FirestoreRecyclerOptions.Builder<requestList>()
                .setQuery(query, requestList.class)
                .build();

         adapter = new FirestoreRecyclerAdapter<requestList, RequestViewHolder>(options) {
            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
                return new RequestViewHolder(view);
            }



            @Override
            protected void onBindViewHolder(@NonNull  RequestViewHolder holder, final int position, @NonNull requestList model) {
                    holder.name.setText(model.getfName());
                holder.phone.setText(model.getPhone());
                holder.company.setText(model.getCompanyType());
                holder.carType.setText(model.getCarType());





                final String lng1 = model.getLng();
                final String lat1 = model.getLat();
                final String phone1 = (String) "+966"+model.getPhone();
                final String phone2 = (String)model.getPhone();



                holder.locationImage.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String uri = "http://maps.google.com/maps?q=loc:" + lat1 + "," + lng1;
                      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                      intent.setPackage("com.google.android.apps.maps");
                      startActivity(intent);
                  }
              });
                holder.whatsImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "https://api.whatsapp.com/send?phone="+phone1;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                holder.phoneImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(phone2.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });




                holder.problem.setText(model.getProblem());
            }
        };

         mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
         mFirestoreList.setAdapter(adapter);




    }

    private class RequestViewHolder extends RecyclerView.ViewHolder  {
        private TextView name,phone,company,carType,lat,lng,problem;
        private  ImageView locationImage,phoneImage,whatsImage;
        private Button buttonD;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            locationImage = itemView.findViewById(R.id.locationPic);
            phoneImage = itemView.findViewById(R.id.phonePic);
            whatsImage = itemView.findViewById(R.id.whatsappPic);
            name = itemView.findViewById(R.id.cName);
            phone= itemView.findViewById(R.id.cPhone);
            company= itemView.findViewById(R.id.companyT);
            carType = itemView.findViewById(R.id.carT);

            problem=itemView.findViewById(R.id.prob);







        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


}