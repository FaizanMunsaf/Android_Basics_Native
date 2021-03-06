package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Arrays;
import java.util.HashMap;

public class add_product extends AppCompatActivity {

    private ImageView backBtn,add_photo;

    private EditText name,description,price;

    private TextView category;

    private Button add_product;

    private SwitchCompat featured;

    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=300;

    private static final int IMAGE_PICK_GALLERY_CODE=400;
    private static final int IMAGE_PICK_CAMERA_CODE=500;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_product);

        backBtn=findViewById(R.id.back_btn);

        add_photo=findViewById(R.id.add_photo);

        name=findViewById(R.id.name);

        description=findViewById(R.id.description);

        price=findViewById(R.id.price);

        category=findViewById(R.id.category);

        add_product=findViewById(R.id.add_product);

        featured=findViewById(R.id.featured);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        cameraPermissions=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePickDialog();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialog();
            }
        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });
    }

    private String productTitle,productDescription,productCategory,isFeatured;
    private int productQuantity=0,productPrice=0;
    private boolean isfeatured;

    public void inputData(){
        productTitle=name.getText().toString().trim();
        productDescription=description.getText().toString().trim();
        productCategory=category.getText().toString().trim();
        productPrice= Integer.parseInt(price.getText().toString().trim());
        isfeatured=featured.isChecked();

        if(productTitle.equals("")){
            Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show();
            return;
        }else if(productDescription.equals("")){
            Toast.makeText(this, "Description is Required", Toast.LENGTH_SHORT).show();
            return;
        }else if(productPrice == 0){
            Toast.makeText(this, "Price is Required", Toast.LENGTH_SHORT).show();
            return;
        }else if(productCategory.equals("")){
            Toast.makeText(this, "Categories is Required", Toast.LENGTH_SHORT).show();
            return;
        }

        addProduct();
    }

    public void addProduct(){
        progressDialog.setMessage("Adding Product...");
        progressDialog.show();

        String timestamp=""+System.currentTimeMillis();

        if(imageUri == null){
            HashMap<String, Object> hashMap=new HashMap<>();
            hashMap.put("Category",productCategory);
            hashMap.put("Description",productDescription);
            hashMap.put("Featured",isfeatured);
            hashMap.put("Name",productTitle);
            hashMap.put("Price",productPrice);
            hashMap.put("Rating","4");
            hashMap.put("Images", Arrays.asList("https://firebasestorage.googleapis.com/v0/b/cartlog-df22c.appspot.com/o/slider-images%2FImage_not_available.png?alt=media&token=477f84ab-4b02-4ba1-a994-0b01e8d9d663"));
            CollectionReference _ref = FirebaseFirestore.getInstance().collection("Products");
            _ref.document()
                    .set(hashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressDialog.dismiss();
                            Toast.makeText(com.example.ecom_project.add_product.this, "Products Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(com.example.ecom_project.add_product.this,products.class));
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(com.example.ecom_project.add_product.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            String filePathName="product_images/"+""+timestamp;

            StorageReference storageReference= FirebaseStorage.getInstance().getReference(filePathName);
            storageReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();

                            while(!uriTask.isSuccessful());
                            Uri downloadImageUri=uriTask.getResult();

                            if(uriTask.isSuccessful()){
                                HashMap<String, Object> hashMap=new HashMap<>();
                                hashMap.put("Category",productCategory);
                                hashMap.put("Description",productDescription);
                                hashMap.put("Featured",isfeatured);
                                hashMap.put("Name",productTitle);
                                hashMap.put("Price",productPrice);
                                hashMap.put("Rating","4");
                                hashMap.put("Images", Arrays.asList(""+downloadImageUri));
                                CollectionReference _ref = FirebaseFirestore.getInstance().collection("Products");
                                _ref.document()
                                        .set(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressDialog.dismiss();
                                                Toast.makeText(com.example.ecom_project.add_product.this, "Products Added", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(com.example.ecom_project.add_product.this,products.class));
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                progressDialog.dismiss();
                                                Toast.makeText(com.example.ecom_project.add_product.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(com.example.ecom_project.add_product.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void categoryDialog(){
        String[] total_categories={
                "Apparel",
                "Cosmetics",
                "Equipments",
                "Footwear",
                "Furniture",
                "Gadgets",
                "Gaming",
                "Handbag",
                "Jewelry"
        };
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Products Category")
                .setItems(total_categories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String category_text=total_categories[i];
                        category.setText(category_text);
                    }
                }).show();
    }

    public void showImagePickDialog(){

        String[] options={"Camera","Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Pick Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            if(checkCameraPermissions()){
                                pickFromCamera();
                            }else{
                                requestCameraPermission();
                            }
                        }else{
                            if(checkStoragePermissions()){
                                pickFromGallery();
                            }else{
                                requestStoragePermissions();
                            }
                        }
                    }
                }).show();

    }

    public void pickFromGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE);
    }

    public void pickFromCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title");
        values.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");

        imageUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,IMAGE_PICK_CAMERA_CODE);
    }

    public boolean checkStoragePermissions(){
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);
        return result;
    }
    private void requestStoragePermissions(){
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermissions(){
        boolean result= ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) ==
                (PackageManager.PERMISSION_GRANTED);

        boolean result1= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length > 0){
                    boolean cameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }else{
                        Toast.makeText(this, "Camera & Storage Permissions Required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            case STORAGE_REQUEST_CODE:{
                if(grantResults.length > 0){
                    boolean storageAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if(storageAccepted){
                        pickFromGallery();
                    }else{
                        Toast.makeText(this, "Storage Permissions Required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){

            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                imageUri=data.getData();

                add_photo.setImageURI(imageUri);
            }else if(requestCode == IMAGE_PICK_CAMERA_CODE){
                add_photo.setImageURI(imageUri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}