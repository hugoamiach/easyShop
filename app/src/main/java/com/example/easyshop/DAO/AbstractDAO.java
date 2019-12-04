package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Entities.AbstractEntities;
import com.example.easyshop.Produit;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class AbstractDAO<TEntities extends AbstractEntities> {
    private DataBase dataBase;
    private Context context;
    private Realm realm;
    private Class<TEntities> aClass;

    public AbstractDAO(Context context, Class<TEntities> tEntities) {
        this.dataBase = DataBase.getInstance();
        this.context = context;
        this.realm = dataBase.getRealm(context);
        this.aClass = tEntities;
    }

    public List<TEntities> getAll() {
        return realm.where(aClass).findAll();
    }

    public TEntities find(int id) {
        return realm.where(aClass).equalTo("id", id).findFirst();
    }

    public int getLastId() {
        return realm.where(aClass).max("id").intValue();
    }


    public void delete(TEntities tEntities) {
        realm.beginTransaction();
        realm
                .where(this.aClass)
                .equalTo("id", tEntities.getId());
        realm.commitTransaction();

    }

    public void deleteAll(List<TEntities> liste) {
        for (TEntities entryEntities : liste) {
            delete(entryEntities);
        }
    }

    public TEntities create(TEntities entities) {
        realm.beginTransaction();
        TEntities realmObject = realm.createObject(aClass, getLastId() + 1);
        TEntities realmProduit = realm.copyToRealm(entities);
        realm.commitTransaction();
        return realmObject;
    }
}
