package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class moreinfo extends AppCompatActivity {


    EditText name,des,date;
    Button btn,btn_re;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo2);

        name = findViewById(R.id.name);
        des = findViewById(R.id.loc_des);
        date = findViewById(R.id.dob);
        btn = findViewById(R.id.loginbtn);
        btn_re = findViewById(R.id.Recycler_btn);
        DB = new DBHelper(moreinfo.this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = name.getText().toString();
                String desc = des.getText().toString();
                String date_ = date.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(names,desc,date_);
                if (checkinsertdata == true){
                    Toast.makeText(moreinfo.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(moreinfo.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(moreinfo.this,listview.class));
            }
        });



    }
}