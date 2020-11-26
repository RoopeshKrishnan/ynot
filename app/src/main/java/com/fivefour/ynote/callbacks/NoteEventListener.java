package com.fivefour.ynote.callbacks;

import com.fivefour.ynote.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);

    void onNoteLongClick(Note note);


}
