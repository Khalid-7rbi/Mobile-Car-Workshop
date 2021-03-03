package com.example.mobilecarworkshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class recycleView extends AppCompatActivity {
    private RecyclerView mFirestoreList;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    ArrayList<requestList>list;
    private FirestoreRecyclerAdapter adapter;
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
            protected void onBindViewHolder(@NonNull RequestViewHolder holder, int position, @NonNull requestList model) {
                    holder.name.setText(model.getfName());
                holder.phone.setText(model.getPhone());
                holder.company.setText(model.getCompanyType());
                holder.carType.setText(model.getCarType());
                holder.lat.setText(model.getLat());
                holder.lng.setText(model.getLng());
                holder.problem.setText(model.getProblem());
            }
        };
         mFirestoreList.setHasFixedSize(true);
         mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
         mFirestoreList.setAdapter(adapter);
    }

    private class RequestViewHolder extends RecyclerView.ViewHolder {
        private TextView name,phone,company,carType,lat,lng,problem;
        private ImageView locationImage;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cName);
            phone= itemView.findViewById(R.id.cPhone);
            company= itemView.findViewById(R.id.companyT);
            carType = itemView.findViewById(R.id.carT);
            lat= itemView.findViewById(R.id.cLat);
            lng = itemView.findViewById(R.id.cLng);
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