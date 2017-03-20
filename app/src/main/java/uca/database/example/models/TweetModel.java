package uca.database.example.models;

import io.realm.RealmObject;

public class TweetModel extends RealmObject {
    private String text;
    private UserModel userModel;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
