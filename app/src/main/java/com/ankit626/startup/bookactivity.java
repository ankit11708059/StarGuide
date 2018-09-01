package com.ankit626.startup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookactivity extends AppCompatActivity {

    private String tour=null;
    private String na,pr,de;
    private DatabaseReference mDatabase,user;
    private TextView t1,t2,t3;
    private Button booknow;
    private FirebaseAuth mAuth;
    private FirebaseUser current_user;
    private DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tour=getIntent().getExtras().getString("Tourist Id");
        setContentView(R.layout.activity_bookactivity);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        booknow=(Button)findViewById(R.id.booknow);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Star Guide");
        mAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference().child("Orders");
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference neworders=mref.push();
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        neworders.child("item name").setValue(na);
                        neworders.child("username").setValue(dataSnapshot.child("Name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(bookactivity.this,payment.class));
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        current_user=mAuth.getCurrentUser();
        user=FirebaseDatabase.getInstance().getReference().child("users").child(current_user.getUid());

        mDatabase.child(tour).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                na=(String)dataSnapshot.child("name").getValue();
                pr=(String)dataSnapshot.child("price").getValue();
                de=(String)dataSnapshot.child("desc").getValue();

                t1.setText(na);
                t2.setText(pr);
                t3.setText(de);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
