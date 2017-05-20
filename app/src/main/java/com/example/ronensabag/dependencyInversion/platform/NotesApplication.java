package com.example.ronensabag.dependencyInversion.platform;

import android.app.Application;

import com.example.ronensabag.dependencyInversion.storage.NotesStorage;
import com.example.ronensabag.dependencyInversion.storage.PersistentNotesStorage;

public class NotesApplication extends Application {

    private NotesStorage mNotesStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        initStorage();
    }

    public NotesStorage getNotesStorage() {
        return mNotesStorage;
    }

    private void initStorage() {
//        mNotesStorage = new InMemoryNotesStorage();
        mNotesStorage = new PersistentNotesStorage(this);
    }
}
