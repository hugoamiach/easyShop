package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Produit;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DataBase {
    private Realm realm;
    private static final DataBase ourInstance = new DataBase();

    public static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
    }

    public Realm getRealm(Context context) {
        if (realm == null)
            init(context);
        return realm;
    }

    private void init(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }
}
