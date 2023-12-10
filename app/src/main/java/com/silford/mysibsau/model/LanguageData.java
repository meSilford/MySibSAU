package com.silford.mysibsau.model;

import android.app.Activity;
import android.widget.ImageView;

public class LanguageData {
    int id;
    String Icon;
    String Title, SubTitle;
    Boolean Selected;
    Activity Activity;

    public LanguageData(int id, String icon, String title, String subTitle, Boolean selected, android.app.Activity activity) {
        this.id = id;
        Icon = icon;
        Title = title;
        SubTitle = subTitle;
        Selected = selected;
        Activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }

    public android.app.Activity getActivity() {
        return Activity;
    }

    public void setActivity(android.app.Activity activity) {
        Activity = activity;
    }
}
