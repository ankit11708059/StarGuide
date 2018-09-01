package com.ankit626.startup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {
   private EditText email,pass;
   private TextView ghj;
   private Button btn;
   private DatabaseReference mDatabase;
   private FirebaseAuth mAuth;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    email=(EditText)findViewById(R.id.editEmail);
    pass=(EditText)findViewById(R.id.editPass);
    ghj=(TextView)findViewById(R.id.ghj);
    btn=(Button)findViewById(R.id.btn1);
    progressBar=(ProgressBar)findViewById(R.id.progress_bar);


    mAuth=FirebaseAuth.getInstance();
    mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        progressBar.setVisibility(View.GONE);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           final String emailtext=email.getText().toString().trim();
            String Passtext=email.getText().toString().trim();

            if(!TextUtils.isEmpty(emailtext) && !TextUtils.isEmpty(Passtext)){
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(emailtext,Passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String user=mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user=mDatabase.child(user);
                            current_user.child("Name").setValue(emailtext);
                            Intent abc= new Intent(Main3Activity.this,StatesActivity.class);
                            startActivity(abc);
                            progressBar.setVisibility(View.GONE);

                        }
                        else{
                            Toast.makeText(Main3Activity.this,"",Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }

        }
    });

    }



}
