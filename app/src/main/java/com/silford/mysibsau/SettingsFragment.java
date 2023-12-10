package com.silford.mysibsau;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ImageView bnt_goToProfile;
    Switch OnDarkTheme, OnSystemTheme;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        sharedPreferences = getActivity().getSharedPreferences("GLOBAL_SETTINGS", Context.MODE_PRIVATE);

        bnt_goToProfile = view.findViewById(R.id.btn_goToProfile);
        OnDarkTheme = view.findViewById(R.id.SWITCH_OnDarkTheme);
        OnSystemTheme = view.findViewById(R.id.SWITCH_OnSystemTheme);

        bnt_goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new ProfileFragment()).commit();
                BottomNavigationView bnw = getActivity().findViewById(R.id.BottomNavigationView);
                bnw.setVisibility(View.VISIBLE);
            }
        });

        OnDarkTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OnDarkTheme.isChecked())
                {
                    sharedPreferences.edit().putBoolean("isNightTheme", true).apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else
                {
                    sharedPreferences.edit().putBoolean("isNightTheme", false).apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                Log.v("isNightTheme", "isNightTheme: " + String.valueOf(sharedPreferences.getBoolean("isNightTheme", false)));
            }
        });
        OnSystemTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OnSystemTheme.isChecked())
                {
                    sharedPreferences.edit().putBoolean("isSystemTheme", true).apply();
                    OnDarkTheme.setClickable(false);
                    Log.d("isNightThemeOnMobile() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO", String.valueOf(isNightThemeOnMobile() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO));
                    AppCompatDelegate.setDefaultNightMode(isNightThemeOnMobile() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                }
                else
                {
                    sharedPreferences.edit().putBoolean("isSystemTheme", false).apply();
                    OnDarkTheme.setClickable(true);
                }
                Log.v("isSystemTheme", "isSystemTheme: " + String.valueOf(sharedPreferences.getBoolean("isSystemTheme", false)));
            }
        });


        OnDarkTheme.setChecked(sharedPreferences.getBoolean("isNightTheme", false));
        OnSystemTheme.setChecked(sharedPreferences.getBoolean("isSystemTheme", false));
        OnDarkTheme.setClickable(!sharedPreferences.getBoolean("isSystemTheme", false));

        TextView localizationText = view.findViewById(R.id.SETTINGS_Localization);

        localizationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new LanguagesFragment()).commit();
            }
        });

        String lang = Locale.getDefault().getDisplayLanguage();
        localizationText.setText(lang.substring(0, 1).toUpperCase() + lang.substring(1));

        return view;
    }

    public boolean isNightThemeOnMobile()
    {
        switch (getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
        {
            case Configuration.UI_MODE_NIGHT_YES: return true;
            case Configuration.UI_MODE_NIGHT_NO: return false;
        }
        return false;
    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}