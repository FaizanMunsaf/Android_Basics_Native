package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView login,signup;
    Button btn_signin;
    ViewGroup signuplayout,loginlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.sign_up);
        btn_signin = findViewById(R.id.singIn);
        signuplayout = findViewById(R.id.sign_uplayout);
        loginlayout = findViewById(R.id.loginlayout);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup.setBackground() = getResources().getDrawable(R.drawable.switch_trcks,null);
                signup.setTextColor(getResources().getColor(R.color.purpleColor,null));
                signup.getBackground() = null;

            }
        });

    }
}