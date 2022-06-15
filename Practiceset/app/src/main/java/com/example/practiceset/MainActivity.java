package com.example.practiceset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user,email,pass,phone;

    Button insert,delete,update,view;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.Delete);
        user = findViewById(R.id.etuser);
        email = findViewById(R.id.etemail);
        pass = findViewById(R.id.etpass);
        phone = findViewById(R.id.etcontact);
        DB = new DBHelper(MainActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user.getText().toString();
                String mail = email.getText().toString();
                String pwd = pass.getText().toString();
                String contact = phone.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(name,mail,pwd,contact);
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
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("Email : "+res.getString(1)+"\n");
                    buffer.append("Contact : "+res.getString(3)+"\n\n");
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