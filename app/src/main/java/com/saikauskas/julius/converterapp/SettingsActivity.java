package com.saikauskas.julius.converterapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SettingsActivity extends AppCompatActivity {

    AdView settingsAdView;

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
        setContentView(R.layout.activity_settings);

        ImageView ivBackArrowSettings = findViewById(R.id.ivBackArrowSettings);
        ImageView ivBackArrowWhite = findViewById(R.id.ivBackArrowWhite);

        TextView tvPressDevInsta = findViewById(R.id.tvPressDevInsta);
        TextView ivPressFeatureSuggest = findViewById(R.id.tvPressFeatureSuggest);
        TextView ivReportBug = findViewById(R.id.tvReportBug);
        TextView ivRateApp = findViewById(R.id.tvRateApp);
        TextView themeBttn = findViewById(R.id.tvThemes);

        ImageView instawhite = findViewById(R.id.instaicon);
        ImageView instablack = findViewById(R.id.instaiconlight);

        ImageView featurewhite = findViewById(R.id.feature);
        ImageView featureblack = findViewById(R.id.featurelight);

        ImageView bugwhite = findViewById(R.id.bug);
        ImageView bugblack = findViewById(R.id.buglight);

        ImageView ratewhite = findViewById(R.id.ratingstar);
        ImageView rateblack = findViewById(R.id.ratingstarlight);

        ImageView themewhite = findViewById(R.id.themes);
        ImageView themeblack = findViewById(R.id.themeslight);


        settingsAdView = findViewById(R.id.settingsAdView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        settingsAdView.loadAd(adRequest);

        if (sharedPref.loadLightTheme() == true) {

            instawhite.setVisibility(View.GONE);
            featurewhite.setVisibility(View.GONE);
            bugwhite.setVisibility(View.GONE);
            ratewhite.setVisibility(View.GONE);
            themewhite.setVisibility(View.GONE);


            instablack.setVisibility(View.VISIBLE);
            featureblack.setVisibility(View.VISIBLE);
            bugblack.setVisibility(View.VISIBLE);
            rateblack.setVisibility(View.VISIBLE);
            themeblack.setVisibility(View.VISIBLE);

            ivBackArrowWhite.setVisibility(View.GONE);
            ivBackArrowSettings.setVisibility(View.VISIBLE);


        } else if (sharedPref.loadRegularTheme() == true) {

            ivBackArrowWhite.setVisibility(View.GONE);
            ivBackArrowSettings.setVisibility(View.VISIBLE);
        } else {

            instawhite.setVisibility(View.VISIBLE);
            featurewhite.setVisibility(View.VISIBLE);
            bugwhite.setVisibility(View.VISIBLE);
            ratewhite.setVisibility(View.VISIBLE);
            themewhite.setVisibility(View.VISIBLE);


            instablack.setVisibility(View.GONE);
            featureblack.setVisibility(View.GONE);
            bugblack.setVisibility(View.GONE);
            rateblack.setVisibility(View.GONE);
            themeblack.setVisibility(View.GONE);

            ivBackArrowWhite.setVisibility(View.VISIBLE);
            ivBackArrowSettings.setVisibility(View.GONE);

        }


        ivBackArrowSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });

        ivBackArrowWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });


        tvPressDevInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDevInsta();
            }
        });

        ivPressFeatureSuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "juliusaikauskas@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feature for Convy.");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, I suggest you add a feature to your App where...");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        ivReportBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "juliusaikauskas@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Report a bug for Convy.");
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, I found a bug on your App...");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        ivRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.saikauskas.julius.converterapp");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // to take us back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.saikauskas.julius.converterapp")));
                }
            }
        });

        themeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ThemeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void openDevInsta() {
        Uri webpage = Uri.parse("https://www.instagram.com/juliussaik/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}