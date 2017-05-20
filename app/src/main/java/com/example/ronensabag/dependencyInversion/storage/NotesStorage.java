package com.example.ronensabag.dependencyInversion.storage;

import java.util.List;

public interface NotesStorage {
    void addNote(String note);
    List<String> getAllNotes();
    void clear();
}
