package com.example.superior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Second extends AppCompatActivity {
    EditText etuser,etpass,etemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etuser = findViewById(R.id.etuser);
        etemail = findViewById(R.id.etemail);
        etpass = findViewById(R.id.etpass);


        Intent i = getIntent();
        String user = i.getStringExtra("username");
        String email = i.getStringExtra("email");
        String pass = i.getStringExtra("password");

        etuser.setText(user);
        etemail.setText(email);
        etpass.setText(pass);

      //  Toast.makeText(Second.this,user,Toast.LENGTH_SHORT).show();
        //Toast.makeText(Second.this,email,Toast.LENGTH_SHORT).show();
        //Toast.makeText(Second.this,pass,Toast.LENGTH_SHORT).show();

    }
}