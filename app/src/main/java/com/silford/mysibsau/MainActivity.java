package com.silford.mysibsau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("GLOBAL_SETTINGS", Context.MODE_PRIVATE);

        sharedPreferences.edit().putBoolean("isSystemTheme", true).apply();

        if(!sharedPreferences.contains("isNightTheme")){
            sharedPreferences.edit().putBoolean("isNightTheme", false).apply();
        }
        if(!sharedPreferences.contains("isSystemTheme")){
            sharedPreferences.edit().putBoolean("isSystemTheme", false).apply();
        }
        if(sharedPreferences.getBoolean("isSystemTheme", false)) {
            sharedPreferences.edit().putBoolean("isNightTheme", isNightThemeOnMobile()).apply();
        }
        AppCompatDelegate.setDefaultNightMode(sharedPreferences.getBoolean("isNightTheme", false) ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("isNightTheme", "isNightTheme: " + String.valueOf(sharedPreferences.getBoolean("isNightTheme", false)));
        Log.v("isSystemTheme", "isSystemTheme: " + String.valueOf(sharedPreferences.getBoolean("isSystemTheme", false)));

        bottomNavigationView = findViewById(R.id.BottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                switch(item.getItemId())
                {
                    case R.id.nav_news:
                        fragment = new NewsFragment();
                        break;
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }

    public boolean isNightThemeOnMobile()
    {
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES: return true;
            case Configuration.UI_MODE_NIGHT_NO: return false;
        }
        return false;
    }
}