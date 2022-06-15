package com.example.fregmentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button b_1,b_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_1 = findViewById(R.id.btn_1);
        b_2 = findViewById(R.id.btn_2);

        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragmentation_1 F_1 = new Fragmentation_1();
                loadFragment(F_1);

            }
        });

        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragmentation_2 F_2 = new Fragmentation_2();
                loadFragment(F_2);

            }
        });

    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }
}