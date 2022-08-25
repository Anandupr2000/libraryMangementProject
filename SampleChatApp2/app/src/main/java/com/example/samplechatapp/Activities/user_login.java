package com.example.samplechatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.samplechatapp.Activities.OTPactivity;
import com.example.samplechatapp.databinding.ActivityUserLoginBinding;

public class user_login extends AppCompatActivity {
    /**
     * seting up binding
     */
//Before this add this to gradle buildFeatures{
//        viewBinding true
//    }
//    "ActivityUserLoginBinding" is auto generated as dependent on activity created,as of now user_login -> ActivityUserLoginBinding
    ActivityUserLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPactivity.class);
//                sending phone number from this activity to otp activity with a key
                intent.putExtra("phonenumber",binding.phnno.getText().toString());
                startActivity(intent);
            }
        });
    }
}