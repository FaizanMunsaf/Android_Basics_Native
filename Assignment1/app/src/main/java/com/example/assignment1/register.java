package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
EditText user,email,pass,cpass;
Button b1;

    public static final String MY_PRESS_NAME = "MyFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = findViewById(R.id.etuser);
        email = findViewById(R.id.etemail);
        pass = findViewById(R.id.etpass);
        cpass = findViewById(R.id.etcpass);
        b1 = findViewById(R.id.move);

        String name = user.getText().toString();
        String e_mail = email.getText().toString();
        String passw = pass.getText().toString();
        String cpassw = cpass.getText().toString();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savefile();
            }
        });
    }
    public void savefile(){
        String name = user.getText().toString();
        String e_mail = email.getText().toString();
        String passw = pass.getText().toString();
        String cpassw = cpass.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences(MY_PRESS_NAME,MODE_PRIVATE).edit();
        editor.putString("name",name);
        editor.putString("email",e_mail);
        editor.putString("pass",passw);
        editor.putString("cpass",cpassw);
        editor.apply();

        Intent i = new Intent(register.this,MainActivity.class);
        startActivity(i);


    }
}