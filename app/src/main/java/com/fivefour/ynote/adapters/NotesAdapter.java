package com.fivefour.ynote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivefour.ynote.R;
import com.fivefour.ynote.callbacks.NoteEventListener;
import com.fivefour.ynote.model.Note;
import com.fivefour.ynote.utils.NoteUtils;
import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    private Context context;
    private ArrayList<Note> notes;
    private NoteEventListener listener;
    private boolean multiCheckMode = false;

   /* public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }*/

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.note_layout,parent,false);

        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
      final Note note = getNote(position);
      if (note != null){

          holder.noteText.setText(note.getNoteText());
          holder.noteDate.setText(NoteUtils.dateFromLong(note.getNoteDate()));

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  listener.onNoteClick(note);
              }
          });

          holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {

                  listener.onNoteLongClick(note);
                  return false;
              }
          });

          // check checkBox if note selected
          if (multiCheckMode) {
              holder.checkBox.setVisibility(View.VISIBLE); // show checkBox if multiMode on
              holder.checkBox.setChecked(note.isChecked());
          } else holder.checkBox.setVisibility(View.GONE); // hide checkBox if multiMode off

      }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    private Note getNote(int position){

        return notes.get(position);
    }
    public List<Note> getCheckedNotes() {
        List<Note> checkedNotes = new ArrayList<>();
        for (Note n : this.notes) {
            if (n.isChecked())
                checkedNotes.add(n);
        }

        return checkedNotes;
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView noteText,noteDate;
        CheckBox checkBox;
        public NoteHolder( View itemView) {
            super(itemView);
            noteDate= itemView.findViewById(R.id.note_date);
            noteText= itemView.findViewById(R.id.note_text);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

    public void setListener(NoteEventListener listener) {
        this.listener = listener;
    }
    public void setMultiCheckMode(boolean multiCheckMode) {
        this.multiCheckMode = multiCheckMode;
        if (!multiCheckMode)
            for (Note note : this.notes) {
                note.setChecked(false);
            }
        notifyDataSetChanged();
    }

}
