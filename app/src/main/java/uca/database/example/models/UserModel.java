package uca.database.example.models;

import io.realm.RealmObject;

/**
 * Created by polin on 03-20-17.
 */

public class UserModel extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
