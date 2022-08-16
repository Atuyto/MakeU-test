package com.atuyto.makeu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.Fragments.EntreinementFragment;
import com.atuyto.makeu.Fragments.HomeFragment;
import com.atuyto.makeu.PopUp.SignInPopUp;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout loginButton;
    private RelativeLayout signingButton;
    private Intent mainActivity;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.login);

        this.activity = this;

        mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        SignInPopUp SignInPopUp = new SignInPopUp();

        loginButton = findViewById(R.id.BLogIn);
        signingButton = findViewById(R.id.BSignUp);


        signingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInPopUp.SignInPopUp(LoginActivity.this);


            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do verification
                Toast.makeText(LoginActivity.this, "connexion", Toast.LENGTH_SHORT).show();
                startActivity(mainActivity);
                finish();
            }
        });

    }
}