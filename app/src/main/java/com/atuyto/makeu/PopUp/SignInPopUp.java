package com.atuyto.makeu.PopUp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.atuyto.makeu.R;

public class SignInPopUp extends Dialog {


    private String title;

    public SignInPopUp(Activity activity){
        super(activity, com.google.android.material.R.style.Base_ThemeOverlay_AppCompat);
        setContentView(R.layout.popup_signin);
    }
}
