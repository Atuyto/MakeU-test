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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.atuyto.makeu.Fragments.EntreinementFragment;
import com.atuyto.makeu.Fragments.HomeFragment;
import com.atuyto.makeu.PopUp.SignInPopUp;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout loginButton;
    private RelativeLayout signingButton;
    private Intent mainActivity;
    private Activity activity;


    private String Name, FirstName, Email, Password, CheckPassword, PhoneNumber;

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
                SignInPopUp();


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

    public void SignInPopUp(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.CustomDialogTheme);
        final View SignInpopup = getLayoutInflater().inflate(R.layout.popup_signin, null);

        //recuperer les emplacemnt des inputtext
        EditText SignIn_Name = SignInpopup.findViewById(R.id.Buttom_name);
        EditText SignIn_FirstName = SignInpopup.findViewById(R.id.Buttom_Firstname);
        EditText SignIn_Email = SignInpopup.findViewById(R.id.Buttom_mail);
        EditText SignIn_Password = SignInpopup.findViewById(R.id.Buttom_pass);
        EditText SignIn_checkPassword = SignInpopup.findViewById(R.id.Buttom_check_pass);
        EditText SignIn_Phone = SignInpopup.findViewById(R.id.Buttom_phone);


        Button Button_Valide = SignInpopup.findViewById(R.id.Button_valide);



        Button_Valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Recuperer les entre de l'utilisateur
                Name = SignIn_Name.getText().toString();
                FirstName = SignIn_FirstName.getText().toString();
                Email = SignIn_Email.getText().toString();
                Password = SignIn_Password.getText().toString();
                CheckPassword = SignIn_checkPassword.getText().toString();
                PhoneNumber = SignIn_Phone.getText().toString();



                if(Name.equals("") || FirstName.equals("") || Email.equals("") || PhoneNumber.equals("") && !Password.equals(CheckPassword))
                    Toast.makeText(LoginActivity.this,"manque des information", Toast.LENGTH_SHORT).show();
                else{
                    startActivity(mainActivity);
                    finish();
                }


            }
        });

        dialogBuilder.setView(SignInpopup).create().show();



    }



}