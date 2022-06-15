package com.example.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText entity,password;
    Button btn2;
    public static final String MY_PREFS_NAME ="MyFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        entity =findViewById(R.id.en);
        password=findViewById(R.id.password);
        btn2 = findViewById(R.id.btn2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Ghania = entity.getText().toString();
                String pass = password.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE).edit();
                editor.putString("name",Ghania);
                editor.putString("Password",pass);
                editor.apply();

                Intent i =new Intent(MainActivity.this , login.class);
                startActivity(i);
            }
        });
    }
}