package com.example.labtask5ta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id,name,rollno;

    Button insert,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        id = findViewById(R.id.etid);
        name = findViewById(R.id.etname);
        rollno = findViewById(R.id.etrollno);
        DB = new DBHelper(MainActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eid = id.getText().toString();
                String user = name.getText().toString();
                String rollnum = rollno.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(eid,user,rollnum);
                if (checkinsertdata == true){
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entr Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID : "+res.getString(0)+"\n");
                    buffer.append("name : "+res.getString(1)+"\n");
                    buffer.append("RollNo : "+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entries of Registeration");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}