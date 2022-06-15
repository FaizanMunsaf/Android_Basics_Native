package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Second extends AppCompatActivity {
        Button b1;
        EditText etuser,etpass;
        TextView signup;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

            b1 =(Button) findViewById(R.id.loginbtn);
            etuser = findViewById(R.id.name);
            etpass = findViewById(R.id.password);
            signup = findViewById(R.id.forgotpass);

            Intent i = getIntent();
            String name = i.getStringExtra("email");
            String pass = i.getStringExtra("password");

            etuser.setText(name);
            etpass.setText(pass);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Second.this,calculator.class);
                    startActivity(i);
                }
            });

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = etuser.getText().toString();
                    String pass = etpass.getText().toString();
                    if(name.equals(""))
                    {
                        Toast.makeText(Second.this, "Enter your Username", Toast.LENGTH_SHORT).show();

                    }
                    else if(pass.equals(""))
                    {
                        Toast.makeText(Second.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent i = new Intent(Second.this,calculator.class);
                        i.putExtra("username",name);
                        i.putExtra("password",pass);
                        startActivity(i);
                    }
                    //  Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }