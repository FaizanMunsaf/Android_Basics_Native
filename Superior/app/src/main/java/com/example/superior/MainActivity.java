package com.example.superior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b1;
EditText etuser,etpass,etemail,etcpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 =(Button) findViewById(R.id.move);
        etuser = findViewById(R.id.etuser);
        etemail = findViewById(R.id.etemail);
        etpass = findViewById(R.id.etpass);
        etcpass = findViewById(R.id.etcpass);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etuser.getText().toString();
                String email = etemail.getText().toString();
                String pass = etpass.getText().toString();
                String cpass = etcpass.getText().toString();
                if(name.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your Username", Toast.LENGTH_SHORT).show();
                }
                else if(email.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();

                }
                else if(pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }
                else if(cpass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();

                }
                else if(pass.equals(cpass))
                {
                    Intent i = new Intent(MainActivity.this,Second.class);
                    i.putExtra("username",name);
                    i.putExtra("email",email);
                    i.putExtra("password",pass);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "your password isn't same", Toast.LENGTH_SHORT).show();

                }

              //  Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();


            }
        });
    }
}