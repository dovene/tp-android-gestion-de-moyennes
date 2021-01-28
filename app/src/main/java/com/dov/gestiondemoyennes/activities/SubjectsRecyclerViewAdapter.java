package com.dov.gestiondemoyennes.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import com.dov.gestiondemoyennes.R;
import com.dov.gestiondemoyennes.model.Subject;

import java.util.ArrayList;

public class SubjectsRecyclerViewAdapter extends RecyclerView.Adapter<SubjectsRecyclerViewAdapter.SubjectViewHolder> {

    public interface OnDeleteButtonClicked {
        void delete(Subject subject);
    }

    private ArrayList<Subject> subjects;
    private OnDeleteButtonClicked onDeleteButtonClicked;

    public SubjectsRecyclerViewAdapter(ArrayList<Subject> subject, OnDeleteButtonClicked onDeleteButtonClicked) {
        this.subjects = subject;
        this.onDeleteButtonClicked = onDeleteButtonClicked;
    }

    public void setSubjects(ArrayList<Subject> subject) {
        this.subjects = subject;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subject_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        holder.bind(subjects.get(position), onDeleteButtonClicked);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView titleTV;
        AppCompatTextView coefficientTV;
        AppCompatButton deleteBT;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_TV);
            coefficientTV = itemView.findViewById(R.id.coeff_TV);
            deleteBT = itemView.findViewById(R.id.delete_BT);
        }

        public void bind(Subject subject, OnDeleteButtonClicked onDeleteButtonClicked) {
            titleTV.setText(subject.getTitle());
            coefficientTV.setText(String.valueOf(subject.getCoefficient()));
            deleteBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteButtonClicked.delete(subject);
                }
            });
        }
    }
}
