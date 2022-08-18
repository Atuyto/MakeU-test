package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PopUpResetPassword {

    private FirebaseAuth mAuth;


    @SuppressLint("NotConstructor")
    public void PopUpResetPassword(Context LoginActivity){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) LoginActivity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View PopUpReset = inflater.inflate( R.layout.popup_reset_password, null );

        EditText Email_edit = PopUpReset.findViewById(R.id.EmailRestePassword);

        Button buttonReset = PopUpReset.findViewById(R.id.Reset);
        mAuth = FirebaseAuth.getInstance();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = Email_edit.getText().toString().trim();
                FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();



                if(Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    Email_edit.setError("Veuillez indiquer une adresse Email valide");
                    Email_edit.requestFocus();
                    Email_edit.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    Email_edit.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }
                else {
                    Email_edit.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    Email_edit.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }
                mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(LoginActivity, "Vérifier vos emails", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(LoginActivity, "Veuillez réessayer", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        dialogBuilder.setView(PopUpReset).create().show();
    }
}
