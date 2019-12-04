package com.example.easyshop.Entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public abstract class AbstractEntities extends RealmObject {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }
}
