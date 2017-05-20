package com.example.ronensabag.dependencyInversion.storage;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.ronensabag.dependencyInversion.platform.NotesApplication;

import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class PersistentNotesStorageTests extends NotesStorageTests {
    @NonNull
    @Override
    protected NotesStorage getNotesStorage() {
        NotesApplication notesApplication = (NotesApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        return notesApplication.getNotesStorage();
    }
}
