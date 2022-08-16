package com.atuyto.makeu.PopUp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.LoginActivity;
import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPopUp extends AppCompatActivity {

    int CountVerif = 0;
    private String UserSex = "";
    private String Name, FirstName, Email, Password, CheckPassword, PhoneNumber, sizeStr, weightStr;
    private int weight, size;


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
        EditText SignIn_Weight = SignInpopup.findViewById(R.id.Buttom_poids);
        EditText SignIn_Size = SignInpopup.findViewById(R.id.Buttom_taille);

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
                CountVerif = 0;
                //Recuperer les entre de l'utilisateur
                Name = SignIn_Name.getText().toString();
                FirstName = SignIn_FirstName.getText().toString();
                Email = SignIn_Email.getText().toString();
                Password = SignIn_Password.getText().toString();
                CheckPassword = SignIn_checkPassword.getText().toString();
                PhoneNumber = SignIn_Phone.getText().toString();
                sizeStr = SignIn_Size.getText().toString(); //size = Integer.parseInt(sizeStr);
                weightStr = SignIn_Weight.getText().toString(); //weight = Integer.parseInt(weightStr);


                VerifieCoordone(SignIn_Name, Name, LoginActivity);
                VerifieCoordone(SignIn_FirstName, FirstName, LoginActivity);
                VerifieCoordone(SignIn_Email, Email, LoginActivity);
                VerifieCoordone(SignIn_Size, sizeStr, LoginActivity);
                VerifieCoordone(SignIn_Weight, weightStr, LoginActivity);
                VerifMdp(SignIn_Password, SignIn_checkPassword, Password, CheckPassword, LoginActivity);



                if(PhoneNumber.equals("") || PhoneNumber.length() != 10 ){
                    SignIn_Phone.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Phone.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else{
                    CountVerif++;
                    SignIn_Phone.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Phone.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                }

                if(!UserSex.equals(""))
                    CountVerif++;
                else
                    Toast.makeText(LoginActivity, "Veuillez séléctionner votre genre", Toast.LENGTH_SHORT).show();


                if(CountVerif == 9){
                    LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                    dialogBuilder.setCancelable(true);
                    ActivityCompat.finishAffinity((Activity) LoginActivity);

                }

            }
        });

        dialogBuilder.setView(SignInpopup).create().show();

    }

    private void VerifieCoordone(EditText NameEditText, String Name, Context context)
    {

        if(Name.equals("")){
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));
        }
        else{
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_blue));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Active_bottom));
            CountVerif++;

        }

    }

    private void VerifMdp(EditText NameEditText, EditText SecondNameEditText, String Name, String SecondName, Context context)
    {

        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@/#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letter.matcher(Name);
        Matcher hasDigit = digit.matcher(Name);
        Matcher hasSpecial = special.matcher(Name);

        if(Name.length() >= 8 && hasLetter.find() && hasDigit.find() && hasSpecial.find()){
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_blue));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Active_bottom));
            CountVerif++;
            if(Name.equals(SecondName)){
                SecondNameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_blue));
                SecondNameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Active_bottom));
                CountVerif++;
            }else {
                Toast.makeText(context, "Votre mots de passe ne correspond pas", Toast.LENGTH_LONG).show();
                SecondNameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
                SecondNameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));
            }
        }else {
            Toast.makeText(context, "Votre mots de passe ne contient pas de caractere spéciaux", Toast.LENGTH_LONG).show();
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));

        }



    }

}

