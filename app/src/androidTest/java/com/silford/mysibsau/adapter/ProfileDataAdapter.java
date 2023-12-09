package com.silford.mysibsau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.silford.mysibsau.ProfileFragment;
import com.silford.mysibsau.R;
import com.silford.mysibsau.model.ProfileData;

import java.util.List;

public class ProfileDataAdapter extends RecyclerView.Adapter<ProfileDataAdapter.ProfileDataViewHolder> {

    Context context;
    List<ProfileData> ProfileDatasList;

    public ProfileDataAdapter(Context context, List<ProfileData> profileDatasList) {
        this.context = context;
        ProfileDatasList = profileDatasList;
    }

    @NonNull
    @Override
    public ProfileDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ProfileDataItems = LayoutInflater.from(context).inflate(R.layout.profiledata_item, parent, false);
        return new ProfileDataViewHolder(ProfileDataItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileDataViewHolder holder, int position) {
        holder.ProfileDataSubTitle.setText(ProfileDatasList.get(position).getTitle());
        holder.ProfileDataSubTitle.setText(ProfileDatasList.get(position).getSubTitle());
        holder.ProfileDataLine.setVisibility(ProfileDatasList.get(position).getHasLine() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return ProfileDatasList.size();
    }

    public static final class ProfileDataViewHolder extends RecyclerView.ViewHolder{

        TextView ProfileDataTitle, ProfileDataSubTitle;
        CardView ProfileDataLine;

        public ProfileDataViewHolder(@NonNull View itemView) {
            super(itemView);

            ProfileDataTitle = itemView.findViewById(R.id.PROFILEDATA_Title);
            ProfileDataSubTitle = itemView.findViewById(R.id.PROFILEDATA_SubTitle);
            ProfileDataLine = itemView.findViewById(R.id.PROFILEDATA_Line);
        }
    }
}
