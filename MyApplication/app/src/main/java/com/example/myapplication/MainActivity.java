package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button b_2;
EditText user,pass;
TextView t_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_2 = (Button) findViewById(R.id.loginbtn);

        user = findViewById(R.id.name);
        pass = findViewById(R.id.password);
        t_1 = findViewById(R.id.forgotpass);

    t_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("www.google.com"));
            startActivity(intent);
        }
    });

    b_2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = user.getText().toString();
            String pwd = pass.getText().toString();

            Intent i = new Intent(MainActivity.this,second.class);
            i.putExtra("username",name);
            i.putExtra("password",pwd);
            startActivity(i);
        }
    });
    }

}