package com.example.firebasestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button btn;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    ImageView wt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.loginbtn);
        et1 = findViewById(R.id.email);
        et2 = findViewById(R.id.password);
        et3 = findViewById(R.id.phn);
        wt = findViewById(R.id.whatsapp);
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            final String name = et1.getText().toString();
            final String pwd = et2.getText().toString();
            final String phn = et3.getText().toString();

                Log.e("email", name);
                Log.e("password",pwd);
                Log.e("phone",phn);

                auth.createUserWithEmailAndPassword(name,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid;
                            uid = auth.getCurrentUser().getUid();

                            DocumentReference dr = fstore.collection("user").document(uid);

                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("phone",phn);
                            user.put("password",pwd);

                            dr.set(user).addOnCompleteListener(MainActivity.this,new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task1) {
                                    Toast.makeText(MainActivity.this,"Successfully register",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),Welcome.class));
                                }
                            });
                        }
                        else{
                            Log.w("Error","signin error", task.getException());
                            Toast.makeText(MainActivity.this,"Not registered",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });
    }

}