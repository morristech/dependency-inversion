package com.example.ronensabag.dependencyInversion.notes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ronensabag.dependencyInversion.R;

import java.util.ArrayList;
import java.util.List;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private NotesActivity notesActivity;
    private List<String> mNotes;

    NotesAdapter(NotesActivity notesActivity) {
        this.notesActivity = notesActivity;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView noteText;

        NotesViewHolder(View itemView) {
            super(itemView);
            noteText = (TextView) itemView.findViewById(R.id.note_text);
        }
    }

    void setNotes(List<String> notes) {
        mNotes = new ArrayList<>(notes);
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = notesActivity.getLayoutInflater().inflate(R.layout.note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.noteText.setText(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
