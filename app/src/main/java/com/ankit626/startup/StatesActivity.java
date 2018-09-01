package com.ankit626.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StatesActivity extends AppCompatActivity {

    ImageView a,b,c,d,e,f,g,h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);

        a=(ImageView)findViewById(R.id.raj);
        b=(ImageView)findViewById(R.id.delhi);
        c=(ImageView)findViewById(R.id.hp);
        d=(ImageView)findViewById(R.id.bihar);
        e=(ImageView)findViewById(R.id.tamil);
        f=(ImageView)findViewById(R.id.mumb);
        g=(ImageView)findViewById(R.id.utra);
        h=(ImageView)findViewById(R.id.beng);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(StatesActivity.this,MenuActivity.class);
                startActivity(x);
            }
        });
    }
}
