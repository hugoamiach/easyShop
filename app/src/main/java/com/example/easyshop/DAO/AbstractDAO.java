package com.example.easyshop.DAO;

import android.content.Context;

import com.example.easyshop.Entities.IEntities;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;

public abstract class AbstractDAO<TEntities extends RealmObject & IEntities> {
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
        tEntities.deleteFromRealm();
        realm.commitTransaction();
    }

    public void deleteAll(List<TEntities> liste) {
        for (TEntities entryEntities : liste) {
            delete(entryEntities);
        }
    }

    public TEntities update(TEntities tEntities) {

        if (tEntities.isLoaded()) {
            realm.beginTransaction();
            tEntities = realm.copyToRealmOrUpdate(tEntities);
            realm.commitTransaction();
        }

        return tEntities;
    }

    public Realm getRealm() {
        return realm;
    }

    public TEntities create() {
        int id = getLastId() + 1;
        realm.beginTransaction();
        TEntities realmObject = realm.createObject(aClass, id);
        TEntities tEntities = realm.copyToRealm(realmObject);

        realm.commitTransaction();
        return tEntities;
    }
}