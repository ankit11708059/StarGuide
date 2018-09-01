package com.ankit626.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main4Activity extends AppCompatActivity {
    private EditText email,pass;
    private Button btn;
    private FirebaseAuth mAuth;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        email=(EditText)findViewById(R.id.editEmail);
        pass=(EditText)findViewById(R.id.editPass);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        btn=(Button)findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailtext=email.getText().toString().trim();
                String Passtext=pass.getText().toString().trim();

                if(!TextUtils.isEmpty(emailtext) && !TextUtils.isEmpty(Passtext)){
                    mAuth.createUserWithEmailAndPassword(emailtext,Passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                check();
                            }
                            else{
                                Toast.makeText(Main4Activity.this,"You are not registered",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
    public void check(){
    final String userid=mAuth.getCurrentUser().getUid();
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.hasChild(userid)){
                Intent abc= new Intent(Main4Activity.this,StatesActivity.class);
                startActivity(abc);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

    }
}
