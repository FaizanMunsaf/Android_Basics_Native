package com.example.recyclerist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity  {
Button btn,btn_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn = (Button) findViewById(R.id.implicit);
        btn_1 = (Button) findViewById(R.id.db);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.whatsapp.com"));
                startActivity(intent);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

}