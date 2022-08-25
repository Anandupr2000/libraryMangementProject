package com.example.samplechatapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.samplechatapp.Models.user;
import com.example.samplechatapp.R;
import com.example.samplechatapp.Models.credentials;
import com.example.samplechatapp.Models.utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private EditText studentName, studentClass, email, password, conformPassword;
    private TextView passerror, emailerror, nameerror, classerror, confirmpasserror;
    private Button register;
    private ImageView PassIcon1, PassIcon2;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private String Studentname, Studentclass, Email, Password, Password2;
    private DocumentReference documentReference1;
    //    private CollectionReference collectionReference;
    private DatabaseReference db_reference;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();


//        db_reference = FirebaseDatabase.getInstance().getReference().child("users");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registeration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        PassIcon1.setImageResource(R.drawable.ic_pass_show_focused);
                    } else {
                        PassIcon1.setImageResource(R.drawable.ic_pass_hide_focused);
                    }
                } else {
                    if (!password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        PassIcon1.setImageResource(R.drawable.ic_pass_show);
                        //Show Password
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        PassIcon1.setImageResource(R.drawable.ic_pass_hide);
                        //Hide Password
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });
        conformPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!conformPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        PassIcon2.setImageResource(R.drawable.ic_pass_show_focused);
                    } else {
                        PassIcon2.setImageResource(R.drawable.ic_pass_hide_focused);
                    }
                } else {
                    if (!conformPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        PassIcon2.setImageResource(R.drawable.ic_pass_show);
                        //Show Password
                        conformPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        PassIcon2.setImageResource(R.drawable.ic_pass_hide);
                        //Hide Password
                        conformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Studentname = studentName.getText().toString();
                Studentclass = studentClass.getText().toString();
                System.out.println("Studentname : " + Studentname + "\nStudentclass : " + Studentclass);
                Email = email.getText().toString();
                Password = password.getText().toString();
                Password2 = conformPassword.getText().toString();
//                register the utils after sucessfull validation
                validate();
            }
        });
        PassIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    ((ImageView) (v)).setImageResource(R.drawable.ic_pass_show_focused);

                    //Show Password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView) (v)).setImageResource(R.drawable.ic_pass_hide_focused);

                    //Hide Password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        PassIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (conformPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    ((ImageView) (view)).setImageResource(R.drawable.ic_pass_show_focused);

                    //Show Password
                    conformPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ((ImageView) (view)).setImageResource(R.drawable.ic_pass_hide_focused);

                    //Hide Password
                    conformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void validate() {
        if (TextUtils.isEmpty(Studentname)) {
            nameerror.setVisibility(View.VISIBLE);
            nameerror.setText("This Field cann't be kept empty");
        } else if (TextUtils.isEmpty(Studentclass)) {
            classerror.setVisibility(View.VISIBLE);
            classerror.setText("This Field cann't be kept empty");
        } else if (TextUtils.isEmpty(Email)) {
//                    Toast.makeText(RegisterActivity.this,"Empty email",Toast.LENGTH_SHORT).show();
            emailerror.setVisibility(View.VISIBLE);
            emailerror.setText("This Field cann't be kept empty");
        } else if (TextUtils.isEmpty(Password)) {
//                    Toast.makeText(RegisterActivity.this, "Empty password", Toast.LENGTH_SHORT).show();
            passerror.setVisibility(View.VISIBLE);
            passerror.setText("This Field cann't be kept empty");
        } else if (TextUtils.isEmpty(Password2)) {
//                    Toast.makeText(RegisterActivity.this, "Empty password", Toast.LENGTH_SHORT).show();
            confirmpasserror.setVisibility(View.VISIBLE);
            confirmpasserror.setText("This Field cann't be kept empty");
        } else if (Password.length() < 6) {
//                    Toast.makeText(RegisterActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
            passerror.setVisibility(View.VISIBLE);
            passerror.setText("Password too short");
        } else if (Password2.length() < 6) {
//                    Toast.makeText(RegisterActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
            confirmpasserror.setVisibility(View.VISIBLE);
            confirmpasserror.setText("Password too short");
        } else {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Email);

            Matcher matcher1 = PASSWORD_PATTERN.matcher(Password);
            Matcher matcher2 = PASSWORD_PATTERN.matcher(Password2);
            if (matcher.matches()) {
                if (matcher1.matches()) {
                    if (matcher2.matches()) {
                        if (Password.equals(Password2)) {
                            passerror.setVisibility(View.GONE);
                            confirmpasserror.setVisibility(View.GONE);
                            System.out.println("");
                            registerUser();
                        } else {
                            passerror.setVisibility(View.VISIBLE);
                            passerror.setText("Passwords don't match");
                            confirmpasserror.setVisibility(View.VISIBLE);
                            confirmpasserror.setText("Passwords don't match");
                        }
                    } else {
                        confirmpasserror.setVisibility(View.VISIBLE);
                        confirmpasserror.setText("Password must contain atleast one capital letters, one small letter and one digit");
                    }
                } else {
//                            Toast.makeText(RegisterActivity.this,"Password too weak!",Toast.LENGTH_SHORT).show();
                    passerror.setVisibility(View.VISIBLE);
                    passerror.setText("Password must contain atleast one capital letters, one small letter and one digit");
                }
            } else {
//                        Toast.makeText(RegisterActivity.this,"Enter a vaild email address!",Toast.LENGTH_SHORT).show();
                emailerror.setVisibility(View.VISIBLE);
                emailerror.setText("Enter a vaild email address!");
            }
        }
    }

    private void registerUser() {

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Registering..");
        System.out.println("********************progress dialog*********************");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Map<String, Object> User = new HashMap<>();

        User.put(credentials.getStudentNameKey(), Studentname);
        User.put(credentials.getStudentClassKey(), Studentclass);
        User.put(credentials.getEMAIL_KEY(), Email);
        User.put(credentials.getPASSWORD_KEY(), Password);

        System.out.println("utils :" + User);
        documentReference1 = credentials.getDocumentReference(Email);
        utils.getInstance().setEmail(Email);
        utils.getInstance().setName(Studentname);

//        user u = new user(studentName.getText().toString(), auth.getCurrentUser().getUid(), email.getText().toString(), "No Image");
        user u = new user();
        u.setName(studentName.getText().toString());
        u.setPhnNo("No Phone Number");
        u.setUclass(Studentclass);
        u.setEmail(email.getText().toString());
        u.setProfileImage("No Image Selected");

        auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    u.setUclass(Studentclass);
                    u.setPhnNo("none");
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registeration Sucessfull", Toast.LENGTH_SHORT).show();
                    System.out.println("Registeration Sucessfull");
                    studentName.setText("");
                    studentClass.setText("");
                    email.setText("");
                    password.setText("");
                    conformPassword.setText("");
                    uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    db_reference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                    u.setuID(uid);
                    utils.setuID(uid);

//        adding uid to user map
                    User.put(credentials.getUidKey(), uid);

                    documentReference1.set(User);

                    db_reference.setValue(u).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(), "Data uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Data upload failed", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Registeration Failed", Toast.LENGTH_SHORT).show();
                    System.out.println("Registeration Failed");
                }
            }
        });

    }

    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        finish();
        super.onBackPressed();
    }

    private void initView() {
        studentName = findViewById(R.id.studentName);
        studentClass = findViewById(R.id.studentClass);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conformPassword = findViewById(R.id.password2);
        register = findViewById(R.id.registerBtn);

        PassIcon1 = findViewById(R.id.passIcon);
        PassIcon2 = findViewById(R.id.passIcon2);

        nameerror = findViewById(R.id.nameError);
        classerror = findViewById(R.id.classError);
        passerror = findViewById(R.id.passError);
        emailerror = findViewById(R.id.emailError);
        confirmpasserror = findViewById(R.id.confirmpassError);

        toolbar = findViewById(R.id.toolbar);
//        intializing firebase
        auth = FirebaseAuth.getInstance();

    }

}