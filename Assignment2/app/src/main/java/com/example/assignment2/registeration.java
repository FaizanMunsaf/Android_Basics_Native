package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class registeration extends AppCompatActivity {
    Button b1;
    ImageView bk;
    EditText etpass,etemail,etdob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        b1 =(Button) findViewById(R.id.loginbtn);
        etemail = findViewById(R.id.email);
        etdob = findViewById(R.id.dob);
        etpass = findViewById(R.id.password);
        bk = findViewById(R.id.back);

        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString();
                String pass = etpass.getText().toString();
                String et_dob = etdob.getText().toString();
                if(email.equals(""))
                {
                    Toast.makeText(registeration.this, "Enter your Email", Toast.LENGTH_SHORT).show();

                }
                else if(pass.equals(""))
                {
                    Toast.makeText(registeration.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }
                else if(et_dob.equals(""))
                {
                    Toast.makeText(registeration.this, "Enter your Date of birth", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(registeration.this,login.class);
                    i.putExtra("email",email);
                    i.putExtra("password",pass);
                    i.putExtra("dob",et_dob);
                    startActivity(i);
                }

                //  Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();


            }
        });
    }
}