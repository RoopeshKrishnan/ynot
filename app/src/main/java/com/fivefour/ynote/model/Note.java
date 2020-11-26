package com.fivefour.ynote.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text")
    private String noteText;

    @ColumnInfo(name = "date")
    private Long noteDate;

    @Ignore
    private boolean checked = false;

    public Note() {
    }

    public Note(String noteText, Long noteDate) {
        this.noteText = noteText;
        this.noteDate = noteDate;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Long getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Long noteDate) {
        this.noteDate = noteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteDate=" + noteDate +
                '}';
    }
}
