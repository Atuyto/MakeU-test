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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.atuyto.makeu.MainActivity;
import com.atuyto.makeu.R;
import com.atuyto.makeu.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInPopUp extends AppCompatActivity {

    int CountVerif = 0;
    private String UserSex = "";
    private String Name, FirstName, Email, Password, CheckPassword, PhoneNumber, sizeStr, weightStr;
    private int weight, size;

    private FirebaseAuth mAuth;

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

        SignIn_Name.setText(null);
        SignIn_FirstName.setText(null);
        SignIn_Email.setText(null);
        SignIn_Password.setText(null);
        SignIn_checkPassword.setText(null);
        SignIn_Phone.setText(null);
        SignIn_Weight.setText(null);
        SignIn_Size.setText(null);

        mAuth = FirebaseAuth.getInstance();

        Button Button_Valide = SignInpopup.findViewById(R.id.Button_valide);
        ImageButton Button_HelpPassword = SignInpopup.findViewById(R.id.ButtonHelpPassword);


        ImageView buttonH, buttonF, buttonA;
        buttonH = SignInpopup.findViewById(R.id.Button_H); buttonF = SignInpopup.findViewById(R.id.Button_F); buttonA = SignInpopup.findViewById(R.id.Button_A);

        Button_HelpPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpMessage(inflater, dialogBuilder);
            }
        });

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
                Name = SignIn_Name.getText().toString().trim();
                FirstName = SignIn_FirstName.getText().toString().trim();
                Email = SignIn_Email.getText().toString().trim();
                Password = SignIn_Password.getText().toString().trim();
                CheckPassword = SignIn_checkPassword.getText().toString().trim();
                PhoneNumber = SignIn_Phone.getText().toString().trim();
                sizeStr = SignIn_Size.getText().toString().trim(); //size = Integer.parseInt(sizeStr);
                weightStr = SignIn_Weight.getText().toString().trim(); //weight = Integer.parseInt(weightStr);


                VerifieCoordone(SignIn_Name, Name, LoginActivity, "Veuillez indiquer votre prénom");
                VerifieCoordone(SignIn_FirstName, FirstName, LoginActivity, "Veuillez indiquer votre nom");
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches() || Email.isEmpty()){
                    SignIn_Email.setError("Veuillez mettre une adresse Email valide ");
                    SignIn_Email.requestFocus();
                    SignIn_Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                }else {
                    SignIn_Email.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_blue));
                    SignIn_Email.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Active_bottom));
                    CountVerif++;
                }

                VerifieCoordone(SignIn_Size, sizeStr, LoginActivity, "Veuillez indiquer taille");
                VerifieCoordone(SignIn_Weight, weightStr, LoginActivity, "Veuillez indiquer votre poids");
                VerifMdp(SignIn_Password, SignIn_checkPassword, Password, CheckPassword, LoginActivity, inflater, dialogBuilder);



                if(PhoneNumber.equals("") || PhoneNumber.length() != 10 || !Patterns.PHONE.matcher(PhoneNumber).matches()){
                    SignIn_Phone.setBackground(ContextCompat.getDrawable(LoginActivity, R.drawable.button_red));
                    SignIn_Phone.setHintTextColor(ContextCompat.getColor(LoginActivity, R.color.Red));
                    SignIn_Phone.setError("Veuillez mettre un numéros de téléphone ");
                    SignIn_Phone.requestFocus();
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
                    FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

                    if(User.getEmail().equals(Email) ){
                        Toast.makeText(LoginActivity, "Vous etes déjà inscrit", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mAuth.createUserWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            User user = new User(Name, FirstName, Email, PhoneNumber, weightStr, sizeStr, UserSex);

                                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {

                                                                Toast.makeText(LoginActivity, "users register", Toast.LENGTH_SHORT).show();
                                                                LoginActivity.startActivity(new Intent(LoginActivity, MainActivity.class));
                                                                dialogBuilder.setCancelable(true);
                                                                ActivityCompat.finishAffinity((Activity) LoginActivity);
                                                            }
                                                            else{
                                                                Toast.makeText(LoginActivity, "Probleme", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });


                                        }
                                    }
                                });


                    }

                }



            }
        });

        dialogBuilder.setView(SignInpopup).create().show();

    }

    private void VerifieCoordone(EditText NameEditText, String Name, Context context, String message)
    {

        if(Name.isEmpty()){
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));
            NameEditText.setError(message);
            NameEditText.requestFocus();
        }
        else{
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_blue));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Active_bottom));
            CountVerif++;

        }

    }

    private void VerifMdp(EditText NameEditText, EditText SecondNameEditText, String Name, String SecondName, Context context, LayoutInflater inflater, AlertDialog.Builder dialogBuilder )
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
                Toast.makeText(context, "Votre mot de passe ne correspond pas", Toast.LENGTH_LONG).show();
                SecondNameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
                SecondNameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));
            }
        }else {
            NameEditText.setBackground(ContextCompat.getDrawable(context, R.drawable.button_red));
            NameEditText.setHintTextColor(ContextCompat.getColor(context, R.color.Red));
            PopUpMessage( inflater, dialogBuilder);

        }



    }

    private void PopUpMessage(LayoutInflater inflater, AlertDialog.Builder dialogBuilder ){
        final View PopUpMessage = inflater.inflate( R.layout.popup_message, null );

        TextView message = PopUpMessage.findViewById(R.id.MessageText);
        TextView Title = PopUpMessage.findViewById(R.id.TitleText);

        Title.setText(R.string.Mdp);
        message.setText(R.string.dialog_message);
        dialogBuilder.setView(PopUpMessage).create().show();
    }

}

