package com.example.ronensabag.dependencyInversion.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersistentNotesStorage implements NotesStorage {

    private SQLiteDatabase mDatabase;

    public PersistentNotesStorage(Context context) {
        SQLiteOpenHelper openHelper = new SQLiteOpenHelper(context, "notes", null, 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE notesTable (_id INTEGER PRIMARY KEY,note TEXT);");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS notesTable");
                onCreate(db);
            }
        };
        mDatabase = openHelper.getWritableDatabase();
    }

    @Override
    public void addNote(String note) {
        ContentValues values = new ContentValues(1);
        values.put("note", note);
        mDatabase.insert("notesTable", null, values);
    }

    @Override
    public List<String> getAllNotes() {
        ArrayList<String> notes = new ArrayList<>();
        Cursor cursor = mDatabase.query("notesTable", null, null, null, null, null, null);
        if (cursor .moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                notes.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return notes;
    }

    @Override
    public void clear() {
        mDatabase.execSQL("DELETE FROM notesTable;");
    }
}
