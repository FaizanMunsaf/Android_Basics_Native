package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button b1;
    EditText etpass,etemail;
    ImageView bk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 =(Button) findViewById(R.id.loginbtn);
        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.password);

        bk = findViewById(R.id.back);

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,registeration.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString();
                String pass = etpass.getText().toString();
                if(email.equals(""))
                {
                    Toast.makeText(login.this, "Enter your Email", Toast.LENGTH_SHORT).show();

                }
                else if(pass.equals(""))
                {
                    Toast.makeText(login.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent i = new Intent(login.this,aboutus.class);
                    startActivity(i);
                }

                //  Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();


            }
        });
    }
}