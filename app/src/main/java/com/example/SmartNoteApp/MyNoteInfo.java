package com.example.SmartNoteApp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyNoteInfo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        RealmConfiguration configuration = new RealmConfiguration.Builder().name("myNote_DB.realm").build();
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        Realm.setDefaultConfiguration(configuration);
    }
}

