package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.LoginActivity;
import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPopUp extends AppCompatActivity {


    private String UserSex;
    private String Name, FirstName, Email, Password, CheckPassword, PhoneNumber;



    // Création de la popup d'inscription
    @SuppressLint("NotConstructor")
    public void SignInPopUp(Context LoginActivity){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater) LoginActivity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View SignInpopup = inflater.inflate( R.layout.popup_signin, null );



        //recuperer les emplacemnt des inputtext
        EditText SignIn_Name = SignInpopup.findViewById(R.id.Buttom_name);
        EditText SignIn_FirstName = SignInpopup.findViewById(R.id.Buttom_Firstname);
        EditText SignIn_Email = SignInpopup.findViewById(R.id.Buttom_mail);
        EditText SignIn_Password = SignInpopup.findViewById(R.id.Buttom_pass);
        EditText SignIn_checkPassword = SignInpopup.findViewById(R.id.Buttom_check_pass);
        EditText SignIn_Phone = SignInpopup.findViewById(R.id.Buttom_phone);


        Button Button_Valide = SignInpopup.findViewById(R.id.Button_valide);

        ImageView buttonH, buttonF, buttonA;

        buttonH = SignInpopup.findViewById(R.id.Button_H); buttonF = SignInpopup.findViewById(R.id.Button_F); buttonA = SignInpopup.findViewById(R.id.Button_A);

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonH.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_full));
                buttonF.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                buttonA.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                UserSex = "H";
            }
        });

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonF.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_full));
                buttonH.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                buttonA.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                UserSex = "F";
            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonA.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_full));
                buttonF.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                buttonH.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.circle_button_outline));
                UserSex = "A";
            }
        });



        //recupere les donnés isnscrit et fait les verification
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

                int CountVerif = 0;
                Pattern letter = Pattern.compile("[a-zA-z]");
                Pattern digit = Pattern.compile("[0-9]");
                Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");


                Matcher hasLetter = letter.matcher(Password);
                Matcher hasDigit = digit.matcher(Password);
                Matcher hasSpecial = special.matcher(Password);

                if(Name.equals("")) {
                    SignIn_Name.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Name.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else{
                    CountVerif++;
                    SignIn_Name.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Name.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }
                if(FirstName.equals("")){
                    SignIn_FirstName.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_FirstName.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else{
                    CountVerif++;
                    SignIn_FirstName.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_FirstName.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }

                if(Email.equals("")){
                    SignIn_Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else{
                    CountVerif++;
                    SignIn_Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }
                if(PhoneNumber.equals("") || PhoneNumber.length() != 10 ){
                    SignIn_Phone.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Phone.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else{
                    CountVerif++;
                    SignIn_Phone.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Phone.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }

                if(!Password.equals(CheckPassword) || CheckPassword.isEmpty()) {
                    SignIn_checkPassword.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_checkPassword.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                    Toast.makeText(LoginActivity, "Votre mot de passe ne correspond pas", Toast.LENGTH_LONG).show();
                }else{
                    CountVerif++;
                    SignIn_checkPassword.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_checkPassword.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }
                if(Password.length() <= 8 || !hasLetter.find() && !hasDigit.find() && !hasSpecial.find() ) {
                        SignIn_Password.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                        SignIn_Password.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));

                    Toast.makeText(LoginActivity, "test", Toast.LENGTH_LONG).show();
                }else{
                    CountVerif++;
                    SignIn_Password.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Password.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                };

                if(CountVerif == 6){
                    LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                    finish();
                }


            }
        });

        dialogBuilder.setView(SignInpopup).create().show();



    }

}

