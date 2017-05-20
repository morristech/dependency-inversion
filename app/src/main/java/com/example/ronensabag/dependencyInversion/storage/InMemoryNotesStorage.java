package com.example.ronensabag.dependencyInversion.storage;

import java.util.ArrayList;
import java.util.List;

public class InMemoryNotesStorage implements NotesStorage {

    private final List<String> mNotes;

    InMemoryNotesStorage() {
        mNotes = new ArrayList<>();
    }

    @Override
    public void addNote(String note) {
        mNotes.add(note);
    }

    @Override
    public List<String> getAllNotes() {
        return mNotes;
    }

    @Override
    public void clear() {
        mNotes.clear();
    }
}
