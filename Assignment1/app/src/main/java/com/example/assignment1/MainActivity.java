package com.example.assignment1;

import static com.example.assignment1.register.MY_PRESS_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText etpass,etemail;
    TextView signup;
    ImageView fb,tw,wt;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 =(Button) findViewById(R.id.loginbtn);
        etemail = findViewById(R.id.email);
        etpass = findViewById(R.id.password);
        signup = findViewById(R.id.forgotpass);
        fb = findViewById(R.id.facebook);
        wt = findViewById(R.id.whatsapp);
        tw = findViewById(R.id.tweeter);

        SharedPreferences prefs = getSharedPreferences(MY_PRESS_NAME,MODE_PRIVATE);

        String name = prefs.getString("name","");
        String e_mail = prefs.getString("email","");
        String pass = prefs.getString("pass","");

        etemail.setText(name);
        etpass.setText(pass);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.facebook.com"));
                startActivity(intent);
            }
        });
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.twitter.com"));
                startActivity(intent);
            }
        });

        wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.whatsapp.com"));
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Second.class);
                startActivity(i);
            }
        });
        String email = etemail.getText().toString();
        String wpass = etpass.getText().toString();
        if(email.equals(e_mail) && wpass.equals(pass)){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this,Second.class);
                    i.putExtra("email",email);
                    i.putExtra("password",pass);
                    startActivity(i);
                }
                //  Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
            }
        });
        }
        else{
            Toast.makeText(MainActivity.this, "You aren't registered", Toast.LENGTH_SHORT).show();
        }
    }
}