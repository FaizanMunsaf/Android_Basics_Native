package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class listview extends AppCompatActivity {

    RecyclerView recyclerlist;
    RecyclerAdapter adapter;
    Context context;
    ArrayList<UserModel> mylist;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        recyclerlist = findViewById(R.id.recyclerlist);
        DB = new DBHelper(listview.this);

        mylist = new ArrayList<>();

        //this is a first method to use constructor usermodel class


        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(listview.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()){
           Toast.makeText(listview.this,"hello world!",Toast.LENGTH_SHORT).show();
           mylist.add( new UserModel("Name : "+res.getString(0),"Description : "+res.getString(1),"Date : "+res.getString(2)));

        }




        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false );
        recyclerlist.setLayoutManager(manager);

        adapter = new RecyclerAdapter(context,mylist);
        recyclerlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}