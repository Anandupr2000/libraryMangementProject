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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.samplechatapp.Models.credentials;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


public class Login extends AppCompatActivity {

    private Button login, register;
    private TextView emailerr, passerror;
    private EditText email, pass;
    private String uName, uPass;
    private ImageView icon;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    //    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    "(?=.*[0-9])" +         //at least 1 digit
//                    "(?=.*[a-z])" +         //at least 1 lower case letter
//                    "(?=.*[A-Z])" +         //at least 1 upper case letter
//                    "(?=.*[a-zA-Z])" +      //any letter
//                   // "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                    "(?=\\S+$)" +           //no white spaces
//                    ".{4,}" +               //at least 4 characters
//                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!(credentials.getInstance(this).getLoginData() == null)) {
            System.out.println("credentials.getInstance(this).getLoginData() => "+credentials.getInstance(this).getLoginData());
            startActivity(new Intent(this, MainActivity.class));
        } else {
            System.out.println("credentials.getInstance(this).getLoginData() => "+credentials.getInstance(this).getLoginData());

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setTitle("Login");

            initView();
            pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
//                        make sure that the input type is textpassword otherwise getTransformationMethod() will return null value
//                        when tap to enter password
                        if (!pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                            icon.setImageResource(R.drawable.ic_pass_show_focused);
                        } else {
                            icon.setImageResource(R.drawable.ic_pass_hide_focused);
                        }
                    } else {
//                        outside password textbox
                        if (!pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                            icon.setImageResource(R.drawable.ic_pass_show);
                            //Show Password
                            pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        } else {
                            icon.setImageResource(R.drawable.ic_pass_hide);
                            //Hide Password
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }

                    }
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//                using finish() fn here will destroy the startActivity
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                email id is username
                    uName = email.getText().toString();
                    uPass = pass.getText().toString();
//                    TODO: Values  matched with database => done
//                    uName = "ananduprajesh@gmail.com";
//                    uPass = "Anandu123";


                    if (TextUtils.isEmpty(uName)) {
//                    Toast.makeText(startActivity.this,"Empty email",Toast.LENGTH_LONG).show();
                        emailerr.setVisibility(View.VISIBLE);
                        emailerr.setText("This field cann't be kept empty");
                    } else if (TextUtils.isEmpty(uPass)) {
//                    Toast.makeText(startActivity.this,"Empty password",Toast.LENGTH_LONG).show();
                        passerror.setVisibility(View.VISIBLE);
                        passerror.setText("This field cann't be kept empty");
                    } else {
//                    change error text visiblity to gone
                        emailerr.setVisibility(View.GONE);
                        passerror.setVisibility(View.GONE);
                        // comparision
                        validateUser();
                    }

                }
            });
        }
    }

    private void validateUser() {
//            without using this we can use firebase athentication

//        DocumentReference ref = credentials.getDocumentReference(uName);
//        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists())
//                {
//                    String db_user_name = documentSnapshot.getString(credentials.getEMAIL_KEY());
//                    String db_pass = documentSnapshot.getString(credentials.getPASSWORD_KEY());
//
//                    if(uName.equals(db_user_name)  && uPass.equals(db_pass))
//                    {
////                        validationSucess = true;
//                        Toast.makeText(startActivity.this,"Login Sucess",Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(startActivity.this,MainActivity.class));
//                        finish();
//                    } else
//                    {
//                        Toast.makeText(startActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
//                        System.out.println("Login Failed");
//                    }
//                }else
//                {
//                    Toast.makeText(startActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
//                    System.out.println("Login Failed");
//                }
//            }
//        });

//        using firebase auth method
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Loging in..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        auth.signInWithEmailAndPassword(uName, uPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (utils.getInstance().getuID() == null) {
                        System.out.println("******************** id is null, getting from firestore **********************");

                        DocumentReference ref = credentials.getDocumentReference(uName);
                        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getString(credentials.getUidKey());
                                    credentials.setInstance(getApplicationContext(),id);

                                    System.out.println("utils.getInstance().getuID() => " + id);
                                    if (id == null) {
                                        Toast.makeText(getApplicationContext(), "You doesn't properly registered", Toast.LENGTH_LONG).show();
                                        ref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "Account removed", Toast.LENGTH_LONG).show();

                                                auth.getCurrentUser().delete().addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getApplicationContext(), "Unable to remove account authentication", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(getApplicationContext(), "Unable to remove account", Toast.LENGTH_LONG).show();
                                                    }
                                                });

                                    } else {
                                        utils.getInstance().setuID(id);
                                        try {
                                            System.out.println("id => "+id);
                                            Toast.makeText(getApplicationContext(), "Login Sucess", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                            }
                        });
                        System.out.println("credentials.getInstance(this).getLoginData() => "+credentials.getInstance(getApplicationContext()).getLoginData());
                        progressDialog.dismiss();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                    System.out.println("Login Failed");
                }
            }
        });

        System.out.println("**************************************************************************************");
        System.out.println("credentials.getInstance(this).getLoginData() => "+credentials.getInstance(this).getLoginData());
        System.out.println("utils.getInstance().getuID() => " + utils.getInstance().getuID());

    }

    private void initView() {
        login = findViewById(R.id.loginBtn);
        System.out.println("Login Btn intialized is " + login);
        register = findViewById(R.id.registerBtn);
        System.out.println("Register Btn intialized is " + register);
        email = findViewById(R.id.emailInput);
        pass = findViewById(R.id.passInput);
        icon = findViewById(R.id.show_pass_btn);

        emailerr = findViewById(R.id.emailError);
        passerror = findViewById(R.id.passError);

        auth = FirebaseAuth.getInstance();
    }

    //    for show & hide password
    public void ShowHidePass(View view) {

        try {
            if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                icon.setImageResource(R.drawable.ic_pass_show_focused);

                //Show Password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                icon.setImageResource(R.drawable.ic_pass_hide_focused);

                //Hide Password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        } catch (Exception e) {
            System.out.println(e + " occured");
        }

    }

}