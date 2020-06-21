package com.example.exeercise_lecture17;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("self")
    @Expose
    private self self;
    @SerializedName("edit")
    @Expose
    private Edit edit;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;

    public self getSelf() {
        return self;
    }

    public void setSelf(self self) {
        this.self = self;
    }

    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
