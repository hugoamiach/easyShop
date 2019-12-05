
package com.example.easyshop.Entities;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

public abstract class AbstractEntities implements RealmModel {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }
}

