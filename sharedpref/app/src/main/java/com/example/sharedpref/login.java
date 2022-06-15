package com.example.sharedpref;

import static com.example.sharedpref.MainActivity.MY_PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    EditText entity,pass;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        entity = findViewById(R.id.en);
        pass = findViewById(R.id.password);
        btn1 = findViewById(R.id.btn2);


        SharedPreferences editor = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        String name =editor.getString("name","");
        String password =editor.getString("Password","");
        entity.setText(name);
        pass.setText(password);
    }
}