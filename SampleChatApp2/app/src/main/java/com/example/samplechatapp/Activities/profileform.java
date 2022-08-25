package com.example.samplechatapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.ActivityProfileBinding;
import com.example.samplechatapp.Models.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profileform extends AppCompatActivity {

    /**
     * Binding is done instead of findviewid() method
     */
    ActivityProfileBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private Uri selectedImage;
    private String uname,uId;
    private ProgressDialog progressDialog;
    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

//        ========================== setting actionbar ================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//======================================================================================

        /**
         * TODO: for currently signed in user paste this in login form => done
         */
//
        uId = utils.getInstance().getuID();

        if(auth.getCurrentUser()!=null){
            database.getReference().child("users").child(uId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user u = snapshot.getValue(user.class);
                    if(u.getPhnNo()==null||u.getPhnNo().length()==0){
                        startActivity(new Intent(getApplicationContext(),registerWthPhnNo.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"Failed to fetch data from server",Toast.LENGTH_SHORT).show();
                }
            });
        }
//        TODO: remove this and attach => done
//        auth.signInWithEmailAndPassword("ananduprajesh@gmail.com","Anandu123");

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });

        ProgressDialog progressDialog1 = new ProgressDialog(profileform.this);
        progressDialog1.setMessage("Loading profile");
        progressDialog1.setCancelable(false);
        progressDialog1.show();
        database.getReference().child("users").child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                user u = snapshot.getValue(user.class);
                utils.getInstance().setName(u.getName());
                utils.getInstance().setEmail(u.getEmail());
                utils.getInstance().setIsPic(u.getProfileImage());
                utils.getInstance().setPhoneNo(u.getPhnNo());

                String no;
                if(utils.getInstance().getPhoneNo()==null) {

                    no ="none";
                }else{
                    no = utils.getInstance().getPhoneNo();
                }

                if(u!=null){
                    System.out.println("utils.getInstance().getName() => "+utils.getInstance().getName());
                progressDialog1.dismiss();
                binding.Name.setText("Name : \n"+utils.getInstance().getName());
                binding.email.setText("Email : \n"+utils.getInstance().getEmail());
                binding.phnno.setText("Phone Number : \n"+no);
                Glide.with(getApplicationContext())
                        .load(u.getProfileImage())
                        .placeholder(getResources().getDrawable(R.drawable.avatar_account_dummy_profilepic))
                        .into(binding.imageView);
                }else {
                    progressDialog1.dismiss();
                    Toast.makeText(getApplicationContext(),"Error in loading profile",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });

        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.Name.setEnabled(true);
                binding.email.setEnabled(true);
                binding.phnno.setEnabled(true);
                binding.Name.setText("");
                binding.email.setText("");
                binding.phnno.setText("");
                binding.Name.setHint("Name");
                binding.Name.setHintTextColor(Color.BLACK);
                binding.Name.setTextColor(Color.BLACK);
                binding.email.setHint("Email");
                binding.email.setHintTextColor(Color.BLACK);
                binding.email.setTextColor(Color.BLACK);
                binding.phnno.setHint("Phone Number");
                binding.phnno.setHintTextColor(Color.BLACK);
                binding.phnno.setTextColor(Color.BLACK);
                binding.finishBtn.setVisibility(View.VISIBLE);
            }
        });


//      saving the content after filling the form
        binding.finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = binding.Name.getText().toString();
                String email = binding.email.getText().toString();
                if(uname.isEmpty()){
                    binding.Name.setError("Cann't leave this field empty");
                    return;
                }
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(email);
                if(email.isEmpty()){
                    binding.email.setError("Cann't leave this field empty");
                    return;
                }
                if(!matcher.matches()){
                    binding.email.setError("Enter valid email id");
                    return;
                }
                progressDialog = new ProgressDialog(profileform.this);
                progressDialog.setMessage("Setting up profile");
                progressDialog.show();

//                                    TODO: add email from form => done
                String imageUrl = selectedImage.toString();
                System.out.println("utils.getInstance().getuID() : " + utils.getInstance().getuID());
                user u = new user(uname, utils.getInstance().getuID(),email, imageUrl);
                u.setPhnNo(binding.phnno.getText().toString());
                database.getReference()
                        .child("users")
                        .child(uId)
                        .setValue(u).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.dismiss();
                    }
                });

                database.getReference().child("users").child(uId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user u = snapshot.getValue(user.class);
                        utils.getInstance().setName(u.getName());
                        utils.getInstance().setEmail(u.getEmail());
                        utils.getInstance().setIsPic(u.getProfileImage());
                        utils.getInstance().setPhoneNo(u.getPhnNo());

                        binding.Name.setText(utils.getInstance().getName());
                        binding.email.setText(utils.getInstance().getEmail());
                        binding.phnno.setText(utils.getInstance().getPhoneNo());
                        progressDialog.dismiss();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed to get data",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
    /**
     * the imageView Listener open an activity for selecting an image and result is get through following method
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data!=null){
            if(data.getData()!=null){

                ProgressDialog progressDialog2 = new ProgressDialog(profileform.this);
                progressDialog2.setMessage("Uploading..");
                progressDialog2.show();
                binding.imageView.setImageURI(data.getData());
                selectedImage = data.getData();

                StorageReference storageReference = storage.getReference().child("Profile").child(uId);
                try{
                    if(!selectedImage.equals(null)) {
                        storageReference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
//                                  uri is the path of the file
                                            database.getReference().child("users").child(uId).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    user u = snapshot.getValue(user.class);
                                                    u.setProfileImage(uri.toString());
                                                    utils.getInstance().setIsPic(u.getProfileImage());
                                                    database.getReference().child("users").child(uId).setValue(u)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {

//                                                                  updating profile pic

                                                                    Toast.makeText(getApplicationContext(),"Sucessfully updated profile picture",Toast.LENGTH_SHORT).show();
                                                                    Glide.with(getApplicationContext())
                                                                            .load(utils.getIsPic())
                                                                            .placeholder(getResources().getDrawable(R.drawable.avatar_account_dummy_profilepic))
                                                                            .into(binding.imageView);
                                                                }
                                                            })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(getApplicationContext(),"Failed to update profile picture",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    Toast.makeText(getApplicationContext(),"Failed to connect to server",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            progressDialog2.dismiss();
                                        }
                                    });

                                } else {
                                    progressDialog2.dismiss();
                                    Toast.makeText(getApplicationContext(), "Failed to upload data, check your internet connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }catch (NullPointerException e){
                    System.out.println("***************************************************" +
                            "\n************** Exception occured: "+e);
                    progressDialog2.dismiss();
                }
            }
        }
    }

    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        finish();
        super.onBackPressed();
    }
}