package com.atuyto.makeu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.atuyto.makeu.PopUp.SignInPopUp;

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
                createSignInPopUp();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do verification
                Toast.makeText(LoginActivity.this, "connexion", Toast.LENGTH_SHORT ).show();
                startActivity(mainActivity);
                finish();
            }
        });


    }

    public void createSignInPopUp(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        final View SignInpopup = getLayoutInflater().inflate(R.layout.popup_signin, null);
        SignInpopup_FirstName = (TextView) SignInpopup.findViewById((R.id.text_firstName));


        dialogBuilder.setView(SignInpopup).create().show();

    }
}