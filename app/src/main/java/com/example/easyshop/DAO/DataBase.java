package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Product;
import com.example.easyshop.R;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DataBase {
    private Realm realm;
    private static final DataBase ourInstance = new DataBase();
    private static final String baseName = "myrealm.realm";

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

    private void populateBDD(final Context context) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Product.class);
                InputStream is = context.getResources().openRawResource(R.raw.product);
                try {
                    realm.createAllFromJson(Product.class, is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init(Context context) {

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name(baseName).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
       // populateBDD(context);
    }
}
