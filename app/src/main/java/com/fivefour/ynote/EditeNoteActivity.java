package com.fivefour.ynote;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fivefour.ynote.db.NotesDB;
import com.fivefour.ynote.db.NotesDao;
import com.fivefour.ynote.model.Note;

import java.util.Date;

public class EditeNoteActivity extends AppCompatActivity {
    private EditText inputNote;
    private NotesDao dao;
    private Note temp;
    public static final String NOTE_EXTRA_KEY="note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set theme
        SharedPreferences sharedPreferences = getSharedPreferences(com.fivefour.ynote.MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);
        int theme = sharedPreferences.getInt(com.fivefour.ynote.MainActivity.THEME_Key, R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_note);
        Toolbar toolbar = findViewById(R.id.edit_note_activity_toolbar);
        setSupportActionBar(toolbar);

        inputNote= findViewById(R.id.input_note);

      dao=  NotesDB.getInstance(this).notesDao();

      if (getIntent().getExtras()!=null){
           int id=getIntent().getExtras().getInt(NOTE_EXTRA_KEY,0);
          temp=dao.getNoteById(id);
          inputNote.setText(temp.getNoteText());
      } else inputNote.setFocusable(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edite_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id==R.id.save_note)
             onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {

        String text = inputNote.getText().toString();
        if (!text.isEmpty())
        {
            long date = new Date().getTime();

            if (temp==null)
            {
                temp= new Note(text,date);
                dao.insertNote(temp);


            } else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updateNote(temp);

            }
           // temp.setNoteDate(date);
          // temp.setNoteText(text);
           // if (temp.getId()== -1)
             //   dao.insertNote(temp);
          // else
           //   dao.updateNote(temp);
            finish();
        }
    }
}
