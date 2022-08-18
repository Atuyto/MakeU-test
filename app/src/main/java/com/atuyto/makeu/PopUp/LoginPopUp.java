package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPopUp {

    private String password, email;

    private FirebaseAuth mAuth;


    @SuppressLint("NotConstructor")
    public void LoginPopUp(Context LoginActivity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) LoginActivity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View LoginPopUp = inflater.inflate( R.layout.popup_login, null );


        EditText Email = LoginPopUp.findViewById(R.id.Login_Email);
        EditText Password = LoginPopUp.findViewById(R.id.Login_Password);
        TextView mdpOublier = LoginPopUp.findViewById(R.id.MdpOublier);

        Email.setText(null);
        Password.setText(null);
        mdpOublier.setText(null);

        Button button_valide = LoginPopUp.findViewById(R.id.Button_valide);

        mAuth = FirebaseAuth.getInstance();

        mdpOublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpResetPassword popUpResetPassword = new PopUpResetPassword();
                popUpResetPassword.PopUpResetPassword(LoginActivity);
            }
        });

        button_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();

                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Email.setError("Veuillez indiquez une adresse Email");
                    Email.requestFocus();
                    Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }
                else {
                    Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }

                if(password.isEmpty()){
                    Password.setError("Veuillez indiquer votre mot de passe");
                    Password.requestFocus();
                    Password.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    Password.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }
                else {
                    Password.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    Password.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                            dialogBuilder.setCancelable(true);
                            ActivityCompat.finishAffinity((Activity) LoginActivity);

                        }
                        else{
                            Toast.makeText(LoginActivity, "Erreur de connexion, ressayer", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });

        dialogBuilder.setView(LoginPopUp).create().show();

    }
}
