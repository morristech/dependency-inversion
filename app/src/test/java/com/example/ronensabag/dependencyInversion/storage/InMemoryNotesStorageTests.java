package com.example.ronensabag.dependencyInversion.storage;

import android.support.annotation.NonNull;

public class InMemoryNotesStorageTests extends NotesStorageTests {
    @NonNull
    @Override
    protected NotesStorage getNotesStorage() {
        return new InMemoryNotesStorage();
    }
}
