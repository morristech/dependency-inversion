package com.example.ronensabag.dependencyInversion.storage;

import android.support.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public abstract class NotesStorageTests {
    private NotesStorage mNotesStorage;
    private String mFirstNote;
    private String mSecondNote;

    @Before
    public void setupTest() {
        mNotesStorage = getNotesStorage();
        mFirstNote = "this is first test note";
        mSecondNote = "this is second test note";

    }

    @NonNull
    protected abstract NotesStorage getNotesStorage();

    @After
    public void clearStorage() {
        mNotesStorage.clear();
    }

    @Test
    public void getAllNotesReturnAllAddedNotes() {
        mNotesStorage.addNote(mFirstNote);
        mNotesStorage.addNote(mSecondNote);
        List<String> allNotes = mNotesStorage.getAllNotes();
        assertEquals(Arrays.asList(mFirstNote, mSecondNote), allNotes);
    }

    @Test
    public void clearRemoveAllAddedNotes() {
        mNotesStorage.addNote(mFirstNote);
        mNotesStorage.addNote(mSecondNote);
        mNotesStorage.clear();
        List<String> allNotes = mNotesStorage.getAllNotes();
        assertEquals(0, allNotes.size());
    }

    @Test
    public void clearBeforeAddingNotes() {
        mNotesStorage.clear();
        List<String> allNotes = mNotesStorage.getAllNotes();
        assertEquals(0, allNotes.size());
    }

    @Test
    public void addNoteAfterClear() {
        mNotesStorage.addNote(mFirstNote);
        mNotesStorage.addNote(mSecondNote);
        mNotesStorage.clear();
        mNotesStorage.addNote(mSecondNote);
        mNotesStorage.addNote(mFirstNote);
        List<String> allNotes = mNotesStorage.getAllNotes();
        assertEquals(Arrays.asList(mSecondNote, mFirstNote), allNotes);
    }
}