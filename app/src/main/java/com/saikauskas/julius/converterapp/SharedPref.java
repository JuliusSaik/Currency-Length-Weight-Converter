package com.saikauskas.julius.converterapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    SharedPreferences sharedPreferences;

    //recieves the App context
    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    //this method will save the Light mode state : True or False
    public void setRegularTheme(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("RegularTheme", state);
        editor.commit();
    }

    //this method will load the Light mode State
    public Boolean loadRegularTheme() {
        Boolean state = sharedPreferences.getBoolean("RegularTheme", false);
        return state;
    }


    public void setMinimalTheme(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("MinimalTheme", state);
        editor.commit();
    }

    //this method will load the Light mode State
    public Boolean loadMinimalTheme() {
        Boolean state = sharedPreferences.getBoolean("MinimalTheme", false);
        return state;
    }


    public void setLightTheme(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("LightTheme", state);
        editor.commit();
    }

    //this method will load the Light mode State
    public Boolean loadLightTheme() {
        Boolean state = sharedPreferences.getBoolean("LightTheme", false);
        return state;
    }

    public void setMintTheme(Boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("MintTheme", state);
        editor.commit();
    }

    //this method will load the Light mode State
    public Boolean loadMintTheme() {
        Boolean state = sharedPreferences.getBoolean("MintTheme", false);
        return state;
    }


}
