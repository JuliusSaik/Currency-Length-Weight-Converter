package com.saikauskas.julius.converterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeActivity extends AppCompatActivity {

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPref = new SharedPref(this);

        if (sharedPref.loadRegularTheme() == true) {
            setTheme(R.style.AppThemeDark);
        } else if (sharedPref.loadMinimalTheme() == true) {
            setTheme(R.style.AppThemeMinimal);
        } else if (sharedPref.loadLightTheme() == true) {
            setTheme(R.style.AppThemeLight);
        } else if (sharedPref.loadMintTheme() == true) {
            setTheme(R.style.AppThemeDarkMint);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        ImageView ivBackArrowTheme = findViewById(R.id.ivBackArrowTheme);
        ImageView ivBackArrowWhite = findViewById(R.id.ivBackArrowThemeWhite);


        ImageView convyRegular = findViewById(R.id.ivregular);
        ImageView convyMinimal = findViewById(R.id.ivminimal);
        ImageView convyLight = findViewById(R.id.ivlight);
        ImageView convyMint = findViewById(R.id.ivdarkmint);

        if (sharedPref.loadLightTheme() == true) {
            ivBackArrowWhite.setVisibility(View.GONE);
            ivBackArrowTheme.setVisibility(View.VISIBLE);
        } else if (sharedPref.loadRegularTheme() == true) {
            ivBackArrowWhite.setVisibility(View.GONE);
            ivBackArrowTheme.setVisibility(View.VISIBLE);
        } else {
            ivBackArrowWhite.setVisibility(View.VISIBLE);
            ivBackArrowTheme.setVisibility(View.GONE);
        }

        ivBackArrowTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        ivBackArrowWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        convyRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRegularTheme();
            }
        });

        convyMinimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMinimalTheme();
            }
        });

        convyLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLightTheme();
            }
        });

        convyMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMintTheme();
            }
        });
    }

    private void setRegularTheme() {
        sharedPref.setRegularTheme(true);
        sharedPref.setMinimalTheme(false);
        sharedPref.setLightTheme(false);
        sharedPref.setMintTheme(false);

        recreate();
    }

    private void setMinimalTheme() {
        sharedPref.setMinimalTheme(true);
        sharedPref.setRegularTheme(false);
        sharedPref.setLightTheme(false);
        sharedPref.setMintTheme(false);
        recreate();
    }


    private void setLightTheme() {
        sharedPref.setLightTheme(true);
        sharedPref.setMinimalTheme(false);
        sharedPref.setRegularTheme(false);
        sharedPref.setMintTheme(false);
        recreate();
    }

    private void setMintTheme() {
        sharedPref.setMintTheme(true);
        sharedPref.setMinimalTheme(false);
        sharedPref.setLightTheme(false);
        sharedPref.setRegularTheme(false);
        recreate();
    }


}
