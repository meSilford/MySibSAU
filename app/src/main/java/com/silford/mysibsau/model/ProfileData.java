package com.silford.mysibsau.model;

public class ProfileData {
    int id;
    String Title, SubTitle;
    Boolean hasLine;

    public ProfileData(int id, String title, String subTitle, Boolean hasLine) {
        this.id = id;
        Title = title;
        SubTitle = subTitle;
        this.hasLine = hasLine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getHasLine() {
        return hasLine;
    }

    public void setHasLine(Boolean hasLine) {
        this.hasLine = hasLine;
    }
}
