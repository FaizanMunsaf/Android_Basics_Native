package com.example.firebasestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;

public class Welcome extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    TextView te,tpa,tph;
    private FirebaseUser user;
    private String Userid;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        te =findViewById(R.id.tvemail);
        tpa = findViewById(R.id.tvpass);
        tph = findViewById(R.id.tvphone);
        btn =findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        reference = firestore.collection("user").document(userid);

        reference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                te.setText(value.getString("name"));
                tpa.setText(value.getString("password"));
                tph.setText(value.getString("phone"));

            }
        });


       }

    }
