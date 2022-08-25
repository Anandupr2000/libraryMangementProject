package com.example.samplechatapp.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.samplechatapp.databinding.ActivityOTPactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;

import java.util.concurrent.TimeUnit;

public class OTPactivity extends AppCompatActivity {

    ActivityOTPactivityBinding binding;
    private FirebaseAuth auth;

    private String verificationID;
    private ProgressDialog dialog;

    /**
     * for signing in enter the number entered for testing in firebase
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityOTPactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        System.out.println("Progress Dialog starting....");
        dialog.setMessage("Sending OTP...");
        dialog.setCancelable(false);
        dialog.show();

        String phnNo = getIntent().getStringExtra("phonenumber");
        binding.phnLabel.setText("Verify "+phnNo);

        auth = FirebaseAuth.getInstance();
//        TODO: generate random code and sent it to user user java program created
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phnNo)
                .setTimeout(120L, TimeUnit.SECONDS)
                .setActivity(OTPactivity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        System.out.println("Firebase Sucess ");
                    }
                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        System.out.println("FirebaseException : "+e);
                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyID, forceResendingToken);
                        System.out.println("Firebase sending Code -> "+verifyID);
                        verificationID = verifyID;
                        dialog.dismiss();
                    }
                }).build();
        System.out.println("option builded sucessfully");

        PhoneAuthProvider.verifyPhoneNumber(options);

        System.out.println("options sent");

        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                System.out.println("otp : "+otp);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,otp);
                System.out.println("credential : "+credential);
                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"LogIn Sucess",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Logged Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}