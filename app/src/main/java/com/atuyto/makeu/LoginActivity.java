package com.atuyto.makeu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout loginButton;
    Intent mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.login);

        mainActivity = new Intent(LoginActivity.this, MainActivity.class);
        loginButton = findViewById(R.id.BLogIn);



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
}