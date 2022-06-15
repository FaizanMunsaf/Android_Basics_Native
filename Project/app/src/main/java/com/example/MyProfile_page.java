package com.example;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.view.View;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.HashMap;

public class MyProfile_page extends AppCompatActivity {
    EditText Fullname,Email,Password,Phone_No;
    Button update;
    TextView changepass;
    ImageView Profile_image;
    ImageButton Edit_Image,backbtn;

    FirebaseAuth fbauth;
    FirebaseFirestore fstore;
    StorageReference storageReference;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_page);

        Fullname=findViewById(R.id.fullname);
        Email=findViewById(R.id.emailaddress);
        Phone_No=findViewById(R.id.phone_no);
        Password=findViewById(R.id.password);
        Edit_Image=findViewById(R.id.profile_editor);
        Profile_image=findViewById(R.id.profile_pic);
        update=findViewById(R.id.reset_btn_pass);
        changepass=findViewById(R.id.txt_forget_password);
        String UserId;
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
//

        fbauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        UserId=fbauth.getCurrentUser().getUid();
        storageReference= FirebaseStorage.getInstance().getReference();

        backbtn=findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile_page.this,MainActivity.class));
                finish();
            }
        });


        DocumentReference documentreference= fstore.collection("user").document(UserId);
        documentreference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Fullname.setText(value.getString("name"));
                Email.setText(value.getString("email"));
                Password.setText(value.getString("password"));
                Phone_No.setText(value.getString("phone"));
                Picasso.get().load(value.get("image").toString()).into(Profile_image);
            }
        });

        Edit_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Opengalleyintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Opengalleyintent,1000);
            }
        });
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyProfile_page.this,change_password.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                HashMap<String, Object> hashMap=new HashMap<>();
                hashMap.put("name",Fullname.getText().toString());
                hashMap.put("phone",Phone_No.getText().toString());

                FirebaseFirestore.getInstance()
                        .collection("user")
                        .document(UserId)
                        .update(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
                                Toast.makeText(MyProfile_page.this, "User Updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(MyProfile_page.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000)
        {
            if (resultCode== Activity.RESULT_OK)
            {
                Uri imageuri=data.getData();
                //Profile_image.setImageURI(imageuri);
                uploadImagetoFirebase(imageuri);
            }

        }
    }

    private void uploadImagetoFirebase(Uri imageuri) {
        progressDialog.show();
        StorageReference fileref=storageReference.child("users/"+fbauth.getCurrentUser().getUid()+"profile.jpg");
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               // Toast.makeText(MyProfile_page.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isSuccessful());
                Uri downloadImageUri=uriTask.getResult();

                if(uriTask.isSuccessful()){
                    HashMap<String, Object> hashMap=new HashMap<>();
                    FirebaseFirestore.getInstance().collection("user")
                            .document(fbauth.getUid())
                            .update("image",downloadImageUri.toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Picasso.get().load(downloadImageUri.toString()).into(Profile_image);
                                    progressDialog.dismiss();
                                }
                            });

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyProfile_page.this, "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}