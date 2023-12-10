package com.silford.mysibsau;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.silford.mysibsau.adapter.LanguageAdapter;
import com.silford.mysibsau.adapter.ProfileDataAdapter;
import com.silford.mysibsau.model.LanguageData;
import com.silford.mysibsau.model.ProfileData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LanguagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguagesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LanguagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LanguagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LanguagesFragment newInstance(String param1, String param2) {
        LanguagesFragment fragment = new LanguagesFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_languages, container, false);

        RecyclerView LanguageRecycler = view.findViewById(R.id.LanguageRecycler);

        List<LanguageData> ProfileDataList = new ArrayList<>();
        ProfileDataList.add(new LanguageData(1, "i_english", getString(R.string.EnglishTitle), getString(R.string.EnglishSubTitle), (String.valueOf(Locale.getDefault().getLanguage()).contains("en"))? true : false, getActivity()));
        ProfileDataList.add(new LanguageData(2, "i_russian", getString(R.string.RussianTitle), getString(R.string.RussianSubTitle), (String.valueOf(Locale.getDefault().getLanguage()).contains("ru"))? true : false, getActivity()));
        ProfileDataList.add(new LanguageData(3, "i_china", getString(R.string.ChinaTitle), getString(R.string.ChinaSubTitle), (String.valueOf(Locale.getDefault().getLanguage()).contains("zh"))? true : false, getActivity()));

        LanguageRecycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        LanguageAdapter languageAdapter = new LanguageAdapter(getContext(), ProfileDataList);
        LanguageRecycler.setAdapter(languageAdapter);

        ImageView localizationText = view.findViewById(R.id.btn_goToSettingsFromLang);
        localizationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new SettingsFragment()).commit();
            }
        });

        return view;
    }
}