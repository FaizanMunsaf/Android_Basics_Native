package com.example.recyclerist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerlist;
RecyclerAdapter adapter;
Context context;
ArrayList<UserModel> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerlist = findViewById(R.id.recyclerlist);

        mylist = new ArrayList<>();

        //this is a first method to use constructor usermodel class
        UserModel model = new UserModel("Faizan@gmail.com","Faizan",R.drawable.fb);
        UserModel model1 = new UserModel("Ghania@gmial.com","Ghania",R.drawable.wt);


        //this is a second method to use constructor user model class
        UserModel model2 = new UserModel();
        model2.setEmail("Android@gmail.com");
        model2.setName("Android");
        model2.setImage(R.drawable.tw);

        UserModel model3 = new UserModel();
        model3.setEmail("Mobile@gmail.com");
        model3.setName("Mobile");
        model3.setImage(R.drawable.fb);



        mylist.add(model);
        mylist.add(model2);
        mylist.add(model1);
        mylist.add(model3);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false );
        recyclerlist.setLayoutManager(manager);

        adapter = new RecyclerAdapter(context,mylist);
        recyclerlist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}