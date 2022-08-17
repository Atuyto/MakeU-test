package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;

public class LoginPopUp {

    private String password, email;

    @SuppressLint("NotConstructor")
    public void LoginPopUp(Context LoginActivity){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) LoginActivity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View LoginPopUp = inflater.inflate( R.layout.popup_login, null );


        EditText Email = LoginPopUp.findViewById(R.id.Login_Email);
        EditText Password = LoginPopUp.findViewById(R.id.Login_Password);

        Button button_valide = LoginPopUp.findViewById(R.id.Button_valide);



        button_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = Email.getText().toString();
                password = Password.getText().toString();

                LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                dialogBuilder.setCancelable(true);
                ActivityCompat.finishAffinity((Activity) LoginActivity);

            }
        });


        dialogBuilder.setView(LoginPopUp).create().show();

    }
}
