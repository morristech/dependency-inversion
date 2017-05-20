package com.example.ronensabag.dependencyInversion.notes;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ronensabag.dependencyInversion.addNote.AddNoteActivity;
import com.example.ronensabag.dependencyInversion.platform.NotesApplication;
import com.example.ronensabag.dependencyInversion.storage.NotesStorage;
import com.example.ronensabag.dependencyInversion.R;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NotesStorage mNotesStorage;
    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNotesStorage = ((NotesApplication)getApplication()).getNotesStorage();
        RecyclerView notesList = (RecyclerView) findViewById(R.id.notes_list);
        notesList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mNotesAdapter = new NotesAdapter(this);
        mNotesAdapter.setNotes(mNotesStorage.getAllNotes());
        notesList.setAdapter(mNotesAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.
                            makeSceneTransitionAnimation(NotesActivity.this, view,
                                    getString(R.string.fab_shared_element_name));
                    startActivityForResult(intent, 1, options.toBundle());
                } else {
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<String> allNotes = mNotesStorage.getAllNotes();
        mNotesAdapter.setNotes(allNotes);
        mNotesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_clear_notes) {
            mNotesStorage.clear();
            mNotesAdapter.setNotes(new ArrayList<String>());
            mNotesAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            String note = data.getStringExtra("NOTE");
            onAddNote(note);
        }
    }

    public void onAddNote(String note) {
        mNotesStorage.addNote(note);
        List<String> allNotes = mNotesStorage.getAllNotes();
        int addPosition = allNotes.size();
        mNotesAdapter.setNotes(allNotes);
        mNotesAdapter.notifyItemInserted(addPosition);
    }

}
