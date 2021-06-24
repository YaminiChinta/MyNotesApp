package com.example.mynotesapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapater extends RecyclerView .Adapter<NoteAdapater.NoteHolder>  {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    private Context mContext;
    public void NoteAdapater1(Context context,ArrayList<Note> notes){
        this.notes=notes;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  NoteAdapater.NoteHolder holder, int position) {
         Note currentNote = notes.get(position);
         holder.textViewTitle.setText(currentNote.getTitle());
         holder.textViewDescription.setText(currentNote.getDescription());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;

        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }




    class NoteHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_view_title);
            textViewDescription=itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.OnItemClick(notes.get(position));
                    }
                }
            });
        }
    }
    public  interface OnItemClickListener{
        void OnItemClick(Note note);
    }
    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
