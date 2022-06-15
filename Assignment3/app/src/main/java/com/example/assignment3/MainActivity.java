package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,pass,address;
    Button btn1;

    public static final String MY_PREFS_NAME ="MyFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        address = findViewById(R.id.dob);
        btn1 = findViewById(R.id.loginbtn);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = name.getText().toString();
                String password = pass.getText().toString();
                String add = address.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", names);
                editor.putString("Password", password);
                editor.putString("Address", add);
                editor.apply();

                Toast.makeText(MainActivity.this, "Your data save in Shared prefernence ✔", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, moreinfo.class);
                startActivity(i);
            }
        });
    }
}