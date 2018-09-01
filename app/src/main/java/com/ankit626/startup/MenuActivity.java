package com.ankit626.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView mTourism;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mTourism = (RecyclerView) findViewById(R.id.tourlist);
        mTourism.setHasFixedSize(true);
        mTourism.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference("Star Guide");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent abc = new Intent(MenuActivity.this, Main4Activity.class);
                    abc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(abc);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseRecyclerAdapter<Tourism, TourismHolder> th = new FirebaseRecyclerAdapter<Tourism, TourismHolder>(
                Tourism.class,
                R.layout.tourism,
                TourismHolder.class,
                mDatabase


        ) {
            @Override
            protected void populateViewHolder(TourismHolder viewHolder, Tourism model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setPrice(model.getPrice());

                final String tour=getRef(position).getKey().toString();
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent tou=new Intent(MenuActivity.this,bookactivity.class);
                        tou.putExtra("Tourist Id",tour);
                        startActivity(tou);

                    }
                });

            }
        };
            mTourism.setAdapter(th);
    }


    public static class TourismHolder extends RecyclerView.ViewHolder{

        View mview;
        public TourismHolder(View itemView) {
            super(itemView);
            mview=itemView;
        }

   public void setName(String name){
       TextView namey=(TextView)mview.findViewById(R.id.ab);
       namey.setText(name);

    }
        public void setPrice(String price){
            TextView pricey=(TextView)mview.findViewById(R.id.price);
            pricey.setText(price);

        }
        public void setDesc(String desc){
            TextView descy=(TextView)mview.findViewById(R.id.rate);
            descy.setText(desc);

        }


    }



}
