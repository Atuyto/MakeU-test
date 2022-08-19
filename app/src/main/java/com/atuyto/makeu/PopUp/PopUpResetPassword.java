package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PopUpResetPassword {

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verificationId;

    @SuppressLint("NotConstructor")
    public void PopUpResetPassword(Context LoginActivity){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) LoginActivity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View PopUpReset = inflater.inflate( R.layout.popup_reset_password, null );

        EditText Email_edit = PopUpReset.findViewById(R.id.EmailRestePassword);
        EditText Phone_edit = PopUpReset.findViewById(R.id.PhoneNumber);


        Button buttonReset = PopUpReset.findViewById(R.id.Reset);
        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("fr");


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = Email_edit.getText().toString().trim();
                String PhoneNumber = Phone_edit.getText().toString().trim();

                FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

                if(User.getEmail().equals(Email))
                {
                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber(PhoneNumber)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(this)                 // Activity (for callback binding)
                                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);

                    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                            final String code = credential.getSmsCode();
                            if(code != null)
                                verifycode(code);
                        }

                        @Override
                        public void onVerificationFailed(FirebaseException e) {
                            Toast.makeText(LoginActivity, "verification failled", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String s,
                                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
                            super.onCodeSent(s, token);
                            verificationId = s;
                        }
                    };



                }
                else{
                    Toast.makeText(LoginActivity, "Vous netes pas inscrit", Toast.LENGTH_SHORT).show();
                }


            }
        });

        dialogBuilder.setView(PopUpReset).create().show();
    }

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInByCredential(credential);
    }

    private void signInByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                }
            }
        });
    }
}
