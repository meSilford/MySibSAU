package com.silford.mysibsau.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ReportFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.silford.mysibsau.LanguagesFragment;
import com.silford.mysibsau.R;
import com.silford.mysibsau.SettingsFragment;
import com.silford.mysibsau.model.LanguageData;
import com.silford.mysibsau.model.ProfileData;

import java.util.List;
import java.util.Locale;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ProfileDataViewHolder> {

    Context context;
    List<LanguageData> LanguageDatasList;

    public LanguageAdapter(Context context, List<LanguageData> languageDatasList) {
        this.context = context;
        LanguageDatasList = languageDatasList;
    }

    @NonNull
    @Override
    public ProfileDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View LanguageDataItems = LayoutInflater.from(context).inflate(R.layout.language_item, parent, false);
        return new ProfileDataViewHolder(LanguageDataItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileDataViewHolder holder, int position) {
        holder.LanguageTitle.setText(LanguageDatasList.get(position).getTitle());
        holder.LanguageSubTitle.setText(LanguageDatasList.get(position).getSubTitle());

        int imageId = context.getResources().getIdentifier(LanguageDatasList.get(position).getIcon(), "drawable", context.getPackageName());
        holder.LanguageIcon.setImageResource(imageId);

        holder.SelectedIcon.setVisibility(LanguageDatasList.get(position).getSelected() ? ImageView.VISIBLE : ImageView.INVISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Activity activity = LanguageDatasList.get(position).getActivity();
                SharedPreferences sharedPreferences = activity.getSharedPreferences("GLOBAL_SETTINGS", Context.MODE_PRIVATE);

                switch(imageId)
                {
                    case R.drawable.i_english:
                        setLocale(LanguageDatasList.get(position).getActivity(), "en");
                        sharedPreferences.edit().putString("language", "en").apply();
                        break;
                    case R.drawable.i_russian:
                        setLocale(LanguageDatasList.get(position).getActivity(), "ru");
                        sharedPreferences.edit().putString("language", "ru").apply();
                        break;
                    case R.drawable.i_china:
                        setLocale(LanguageDatasList.get(position).getActivity(), "zh");
                        sharedPreferences.edit().putString("language", "zh").apply();
                        break;
                }

                activity.recreate();
                BottomNavigationView bnv = activity.findViewById(R.id.BottomNavigationView);
                bnv.setSelectedItemId(R.id.nav_home);
            }
        });
    }

    @Override
    public int getItemCount() {
        return LanguageDatasList.size();
    }

    public static final class ProfileDataViewHolder extends RecyclerView.ViewHolder{

        TextView LanguageTitle, LanguageSubTitle;
        ImageView LanguageIcon, SelectedIcon;


        public ProfileDataViewHolder(@NonNull View itemView) {
            super(itemView);

            LanguageTitle = itemView.findViewById(R.id.LANGUAGE_Title);
            LanguageSubTitle = itemView.findViewById(R.id.LANGUAGE_SubTitle);
            LanguageIcon = itemView.findViewById(R.id.LANGUAGE_ICON);
            SelectedIcon= itemView.findViewById(R.id.LANGUAGE_Good);
        }
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
