package com.example.samplechatapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.samplechatapp.Models.user;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityRegisterWthPhnNoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class registerWthPhnNo extends AppCompatActivity {

    ActivityRegisterWthPhnNoBinding binding;
    FirebaseDatabase database;
    DatabaseReference db_reference;
    FirebaseStorage storage;

    user accountholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterWthPhnNoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        db_reference = FirebaseDatabase.getInstance().getReference().child("users").child(utils.getInstance().getuID());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registeration");


        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 accountholder= snapshot.getValue(user.class);
                Glide.with(getApplicationContext())
                        .load(accountholder.getProfileImage())
                        .placeholder(getResources().getDrawable(R.mipmap.dummy_pic))
                        .into(binding.imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error connecting to server",Toast.LENGTH_SHORT).show();
            }
        });
        binding.changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });
//    +++++++++++++++++++++++++++++++++++++++++ uploading phone number +++++++++++++++++++++++++++++++++++++++++++++++++++++++

        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.profilePhnNo.getText()==null){
                    binding.profilePhnNo.setError("Enter your phone number to continue");
                }else{
                    accountholder.setPhnNo(binding.profilePhnNo.getText().toString());
                    db_reference.setValue(accountholder).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Sucessfully added number",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("chat",3);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed to upload phone number ",Toast.LENGTH_SHORT).show();
                            System.out.println("phone number uploading failed => "+ e);
                        }
                    });
                }
            }
        });
    }


//    ===============================   Uploading Image from user ===========================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null){
            if(data.getData()!=null){
                binding.imageView.setImageURI(data.getData());
                Uri selectedImage = data.getData();

                storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference().child("Profile").child(utils.getInstance().getEmail());

                storageReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    accountholder.setProfileImage(uri.toString());
                                    db_reference.setValue(accountholder).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(),"Profile updation sucessfull",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Failed to upload picture",Toast.LENGTH_SHORT).show();
                            binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.avatar_account_dummy_profilepic));
                        }
                    }
                });

            }
        }
    }
    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        finish();
        super.onBackPressed();
    }
}