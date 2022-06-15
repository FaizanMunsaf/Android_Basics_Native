package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class second extends AppCompatActivity {
EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.password);

        String user = e1.getText().toString();
        String pass = e2.getText().toString();

        e1.setText(user);
        e2.setText(pass);

    }
}