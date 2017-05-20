package com.example.ronensabag.dependencyInversion.addNote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ronensabag.dependencyInversion.R;


public class AddNoteActivity extends AppCompatActivity {

    private EditText mAddNoteText;
    private boolean isDismissing;

    @Override
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mAddNoteText = (EditText) findViewById(R.id.add_note_text);
        initCancelButton();
        initAddNoteButton();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showKeyboardAfterTransitionEnds();
        }
    }

    private void initAddNoteButton() {
        Button addButton = (Button) findViewById(R.id.add_note_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDismissing = true;
                mAddNoteText.clearFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                Intent data = new Intent();
                String note = ((EditText)findViewById(R.id.add_note_text)).getText().toString();
                data.putExtra("NOTE", note);
                setResult(Activity.RESULT_CANCELED, data);
                supportFinishAfterTransition();
            }
        });
    }

    private void initCancelButton() {
        Button cancelButton = (Button) findViewById(R.id.add_note_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(v);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showKeyboardAfterTransitionEnds() {
        getWindow().getSharedElementEnterTransition().addListener(new SimpleTransitionListener(){
            @Override
            public void onTransitionEnd(Transition transition) {
                if (!isDismissing && mAddNoteText.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(mAddNoteText, InputMethodManager.SHOW_IMPLICIT);
                    View viewById = findViewById(R.id.add_note);
                    viewById.getParent();
                }
            }
        });
    }

    public void dismiss(View view) {
        isDismissing = true;
        mAddNoteText.clearFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        setResult(Activity.RESULT_CANCELED);
        supportFinishAfterTransition();
    }

}
