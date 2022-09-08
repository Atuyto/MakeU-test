package com.atuyto.makeu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.atuyto.makeu.PopUp.LoginPopUp;
import com.atuyto.makeu.PopUp.SignInPopUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private RelativeLayout loginButton;
    private RelativeLayout signingButton;
    private Intent mainActivity;
    private Activity activity;
    private TextView SignInpopup_FirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.login);

        this.activity = this;

        mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        loginButton = findViewById(R.id.BLogIn);
        signingButton = findViewById(R.id.BSignUp);


        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInPopUp SignInPopUp = new SignInPopUp();
                SignInPopUp.SignInPopUp(LoginActivity.this);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginPopUp LoginPopUp = new LoginPopUp();
                LoginPopUp.LoginPopUp(LoginActivity.this);
            }
        });


    }


}