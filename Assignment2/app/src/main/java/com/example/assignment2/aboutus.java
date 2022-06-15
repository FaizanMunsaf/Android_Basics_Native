package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {
EditText texthere;
Button btn;
ImageView bk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        btn = findViewById(R.id.send);
        texthere = findViewById(R.id.etchat);

        bk = findViewById(R.id.back);

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(aboutus.this,login.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(aboutus.this,contactus.class);
                startActivity(i);
            }
        });


    }
}