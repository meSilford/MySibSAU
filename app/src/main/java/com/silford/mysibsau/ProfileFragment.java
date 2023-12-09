package com.silford.mysibsau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.silford.mysibsau.adapter.ProfileDataAdapter;
import com.silford.mysibsau.model.ProfileData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView ProfileDataRecyclerView;
    ProfileDataAdapter ProfileDataAdapter;
    ImageView settingsBtn;

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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileDataRecyclerView = view.findViewById(R.id.RecyclerView_ProfileData);

        List<ProfileData> ProfileDataList = new ArrayList<>();
        ProfileDataList.add(new ProfileData(1, getString(R.string.Birthday), "14.08.2002", true));
        ProfileDataList.add(new ProfileData(2, getString(R.string.Group), "БИС 22-02", true));
        ProfileDataList.add(new ProfileData(3, getString(R.string.Telephone), "+7 (913 040 53-08)", true));
        ProfileDataList.add(new ProfileData(4, getString(R.string.Email), "fearthenock@vk.com", false));

        ProfileDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        ProfileDataAdapter = new ProfileDataAdapter(getContext(), ProfileDataList);
        ProfileDataRecyclerView.setAdapter(ProfileDataAdapter);

        settingsBtn = view.findViewById(R.id.bnt_goToSettings);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new SettingsFragment()).commit();
                BottomNavigationView bnw = getActivity().findViewById(R.id.BottomNavigationView);
                bnw.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}