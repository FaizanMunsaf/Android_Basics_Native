package com.example.implicit;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void OnLaunchWebPageButtonlayout(View view){
    Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.superior.edu.pk"));
    startActivity(intent);
    }
}