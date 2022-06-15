package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] name={"Faizan","Ali","Android","IOS","Mobile"};
    String[] email={"faizan@gmail.com","ali@gmail.com","android@gmial.com","ios@gmail.com","Mobile@gmail.com"};
    int[] image = {R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);


        CustomAdapter adapter = new CustomAdapter(name,email,image,MainActivity.this);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

    }
}