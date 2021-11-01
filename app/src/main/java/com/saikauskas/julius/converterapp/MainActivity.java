package com.saikauskas.julius.converterapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    TextView fragmentConvyName;
    ImageView ivSettingsButton, ivSettingsWhite;
    AdView adView;

    SharedPref sharedPref;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.menuCurr:
                    selectedFragment = new CurrencyFragment();
                    fragmentConvyName.setText("Currency Convy");
                    break;

                case R.id.menuTemp:
                    selectedFragment = new TemperatureFragment();
                    fragmentConvyName.setText("Temperature Convy");
                    break;

                case R.id.menuMeasur:
                    selectedFragment = new MeasurmentFragment();
                    fragmentConvyName.setText("Measurement Convy");
                    break;
            }

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.crossfade1, R.anim.crossfade2, R.anim.crossfade1, R.anim.crossfade2).replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

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
        setContentView(R.layout.activity_main);

        fragmentConvyName = findViewById(R.id.fragmentConvyName);
        ivSettingsButton = findViewById(R.id.ivSettingsBttn);

        ivSettingsWhite = findViewById(R.id.ivSettingsBttnWhite);


        //Creates ad view
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                //Toast.makeText(MainActivity.this, "ads loaded but just doesnt show", Toast.LENGTH_LONG).show();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        if (sharedPref.loadLightTheme() == true) {
            ivSettingsWhite.setVisibility(View.GONE);
            ivSettingsButton.setVisibility(View.VISIBLE);
        } else if (sharedPref.loadRegularTheme() == true) {
            ivSettingsWhite.setVisibility(View.GONE);
            ivSettingsButton.setVisibility(View.VISIBLE);
        } else {
            ivSettingsWhite.setVisibility(View.VISIBLE);
            ivSettingsButton.setVisibility(View.GONE);
        }


        BottomNavigationView navigationView = findViewById(R.id.bottomNavBar);


        ivSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideleft2, R.anim.slideright2);
            }
        });

        ivSettingsWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slideleft2, R.anim.slideright2);
            }
        });

        navigationView.setSelectedItemId(R.id.menuCurr);
        navigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurrencyFragment()).commit();

    }


}
