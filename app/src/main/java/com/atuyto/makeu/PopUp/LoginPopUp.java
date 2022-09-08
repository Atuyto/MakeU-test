package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;
import com.atuyto.makeu.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPopUp {

    public static String UID = null;
    private String password, email;

    private FirebaseAuth mAuth;
    private int Count;

    private boolean passwordvisible;
    private DatabaseReference mDatabase;


    public static String name;
    public static String fisrtName;
    public static String sex;
    public static String weight;
    public static String dataEmail;
    public static String size;

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

        Button button_valide = LoginPopUp.findViewById(R.id.Button_valide);
        ProgressBar splash = LoginPopUp.findViewById(R.id.splash);

        mAuth = FirebaseAuth.getInstance();

        mdpSHowHide(Password);

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
                    Count++;
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
                    Count++;
                }

                if(Count == 2)
                {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                UID = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid()).getKey().trim();
                                splash.setVisibility(View.VISIBLE);
                                updateData();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                                        dialogBuilder.setCancelable(true);
                                        ActivityCompat.finishAffinity((Activity) LoginActivity);
                                    }
                                }, 2000);

                            }
                            else{
                                Toast.makeText(LoginActivity, "Erreur de connexion, ressayer", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity, "Veuillez indiquer des information correcte ", Toast.LENGTH_SHORT).show();
                }





            }
        });

        dialogBuilder.setView(LoginPopUp).create().show();

    }
    @SuppressLint("ClickableViewAccessibility")
    private void mdpSHowHide(EditText EditName){
        EditName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int right = 2;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=EditName.getRight()-EditName.getCompoundDrawables()[right].getBounds().width()){
                        int selection = EditName.getSelectionEnd();
                        if(passwordvisible){
                            EditName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eyes_outline_bar_24, 0);
                            EditName.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordvisible=false;
                        }else {
                            EditName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eyes_outline_nobar_24, 0);
                            EditName.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible=true;
                        }
                        EditName.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }
    private void updateData(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(UID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists())
                    {
                        DataSnapshot dataSnapshot = task.getResult();
                        dataEmail = dataSnapshot.child("email").getValue().toString().trim();
                        name = dataSnapshot.child("name").getValue().toString().trim();
                        fisrtName = dataSnapshot.child("first_name").getValue().toString().trim();
                        sex = dataSnapshot.child("sex").getValue().toString().trim();
                        size = dataSnapshot.child("size").getValue().toString().trim();
                        weight = dataSnapshot.child("weight").getValue().toString().trim();

                        UserInformation userinfo = new UserInformation();
                        userinfo.UserInformation(name, fisrtName, dataEmail, weight, size, sex);

                    }

                }
                else {
                    Log.i("TAG", "erreur");
                }
            }
        });
    }


}
